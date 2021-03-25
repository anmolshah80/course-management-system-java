package CourseManagementSystem;

import java.io.*;
import java.util.*;



public class Instructor extends CourseAdministrator{

    public String[] moduleCodesArray = new String[100];

    public List<String> assignedCourseName = new ArrayList<>();
    public ArrayList<String> studentListInModule = new ArrayList<>();



    // method to check the assigned modules to an instructor and check if the input instructor exists in the database
    private boolean assignedModules(String instructorName, String fileName, int level, boolean viewModulesAssigned, boolean viewStudentsRegisteredAndAddMarks) {
        // clearing the previously stored contents from arrays and lists to store new data within this method
        assignedCourseID.clear();
        assignedCourseName.clear();
        assignedModuleCode.clear();

        Arrays.fill(moduleCodesArray, null);

        String[] moduleDetails;
        int courseID;
        String courseName;
        String moduleCode;
        String moduleName;
        String moduleLeader;
        String moduleType;
        int moduleYear;
        int moduleSemester;
        int assignedModules = 0;


        boolean isInstructorValid = false;




        // check for instructor name in level 4 and level 5 modules
        if (level == 4 || level == 5) {
            try {
                FileReader fR = new FileReader(fileName);
                Scanner sc = new Scanner(fR);


                while (sc.hasNextLine()) {
                    String courseModule = sc.nextLine();
                    moduleDetails = courseModule.split("\t");

                    courseID = Integer.parseInt(moduleDetails[0]);
                    courseName = moduleDetails[1];
                    moduleCode = moduleDetails[2];
                    moduleName = moduleDetails[3];
                    moduleLeader = moduleDetails[4];
                    moduleYear = Integer.parseInt(moduleDetails[5]);
                    moduleSemester = Integer.parseInt(moduleDetails[6]);

                    if (instructorName.equalsIgnoreCase(moduleLeader)) {
                        instructorsName = moduleLeader;
                        courseIDAssigned = courseID;



                        assignedCourseID.add(courseID);
                        assignedCourseName.add(courseName);
                        assignedModuleCode.add(moduleCode);

                        // assigning module codes into a string array to later compare with the modules registered by the student
                        moduleCodesArray[assignedModules] = moduleCode;


                        if(viewModulesAssigned) {
                            System.out.print("\n");
                            System.out.println("Instructor Name: " + moduleLeader);
                            System.out.println("Assigned Course ID: " + courseID);
                            System.out.println("Assigned Course Name: " + courseName);
                            System.out.println("Assigned Module Code: " + moduleCode);

                            System.out.println("Assigned Module Name: " + moduleName);
                            System.out.println("Assigned Year: " + moduleYear);
                            System.out.println("Assigned Semester: " + moduleSemester);
                            System.out.println("Assigned Module Level: " + level);
                        }


                        assignedModules++;
                        isInstructorValid = true;
                    }
                }

                if(assignedModules > 0) {
                    if(viewModulesAssigned) {
                        System.out.println("\n\nTotal modules assigned: " + assignedModules);
                    }

                    if(viewStudentsRegisteredAndAddMarks) {
                        System.out.println("\n----------------Students registered in the modules assigned to the instructor----------------");
                        int countStudents = studentsInThoseModules(assignedModules, level);


                        if (countStudents > 0) {
                            addModuleMarks(instructorName, fileName, level);
                        } else {
                            System.out.println("No students registered with these modules...\n");
                        }
                    }

                }


                fR.close();
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found for level " + level + " modules\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of level " + level + " modules\n");
            }
        }



        // check for instructor name in level 6 modules file
        else if (level == 6) {
            try {
                FileReader fR = new FileReader(fileName);
                Scanner sc = new Scanner(fR);

                    while (sc.hasNextLine()) {
                        String courseModule = sc.nextLine();
                        moduleDetails = courseModule.split("\t");

                        courseID = Integer.parseInt(moduleDetails[0]);
                        courseName = moduleDetails[1];
                        moduleCode = moduleDetails[2];
                        moduleName = moduleDetails[3];
                        moduleLeader = moduleDetails[4];
                        moduleYear = Integer.parseInt(moduleDetails[5]);
                        moduleSemester = Integer.parseInt(moduleDetails[6]);
                        moduleType = moduleDetails[7];


                        if (instructorName.equalsIgnoreCase(moduleLeader)) {
                            instructorsNameLevel6 = moduleLeader;
                            courseIDAssigned = courseID;



                            assignedCourseID.add(courseID);
                            assignedCourseName.add(courseName);
                            assignedModuleCode.add(moduleCode);

                            // assigning module codes into a string array to later compare with the modules registered by the student
                            moduleCodesArray[assignedModules] = moduleCode;

                            if(viewModulesAssigned) {
                                System.out.print("\n");
                                System.out.println("Instructor Name: " + moduleLeader);
                                System.out.println("Assigned Course ID: " + courseID);
                                System.out.println("Assigned Course Name: " + courseName);
                                System.out.println("Assigned Module Code: " + moduleCode);
                                System.out.println("Assigned Module Name: " + moduleName);
                                System.out.println("Assigned Year: " + moduleYear);
                                System.out.println("Assigned Semester: " + moduleSemester);
                                System.out.println("Assigned Module Level: " + level);
                            }


                            assignedModules++;
                            isInstructorValid = true;
                        }
                    }


                if(assignedModules > 0) {
                    if(viewModulesAssigned) {
                        System.out.println("\n\nTotal modules assigned: " + assignedModules);
                    }

                    if(viewStudentsRegisteredAndAddMarks) {
                        System.out.println("\n----------------Students registered in the modules assigned to the instructor----------------");

                        int countStudents = studentsInThoseModules(assignedModules, level);
                        if (countStudents > 0) {
                            addModuleMarks(instructorName, fileName, level);
                        } else {
                            System.out.println("No students registered with these modules...\n");
                        }
                    }
                }


                fR.close();


            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found for level " + level + " modules\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of level " + level + " modules\n");
            }
        }

        return isInstructorValid;

    }




    // method to display the students registered on the modules assigned to the instructors
    private int studentsInThoseModules(int assignedModules, int level) {
        int studentID, courseID;
        String records;
        int i, countStudents = 0;



        if(level == 4 || level == 5) {
            for (int assignedCourseID : assignedCourseID) {
                countStudents = assignLevel4And5Modules(instructorsName, assignedCourseID, level);

                studentListInModule.addAll(getStudentList);

                getStudentList.clear();

            }
        }



        else if (level == 6) {
            try {

                FileReader fW = new FileReader("Student_Module_6_Records.txt");
                BufferedReader bR = new BufferedReader(fW);


                System.out.println("--------------------------------------------------------");
                System.out.println("|    " + "Module Code" + "\t\t|\t\t" + "Students Registered" + "    |");
                System.out.println("--------------------------------------------------------");
                while ((records = bR.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(records, "\t");

                    studentID = Integer.parseInt(st.nextToken());
                    courseID = Integer.parseInt(st.nextToken());
                    moduleID1 = st.nextToken();
                    moduleID2 = st.nextToken();
                    moduleID3 = st.nextToken();
                    moduleID4 = st.nextToken();
                    moduleID5 = st.nextToken();
                    moduleID6 = st.nextToken();
                    moduleID7 = st.nextToken();
                    moduleID8 = st.nextToken();


                    if (moduleID7.equals("0") && moduleID8.equals("0")) {
                        for (i = 0; i < assignedModules; i++) {
                            if (moduleCodesArray[i].equals(moduleID1) || moduleCodesArray[i].equals(moduleID2) || moduleCodesArray[i].equals(moduleID3) || moduleCodesArray[i].equals(moduleID4) || moduleCodesArray[i].equals(moduleID5) || moduleCodesArray[i].equals(moduleID6)) {


                            System.out.println("|    " + moduleCodesArray[i] + "\t\t\t\t|\t\t\t\t" + studentID + "\t\t   |\n");

                            moduleCodesInList.add(moduleCodesArray[i]);
                            moduleCodesInSet.add(moduleCodesArray[i]);
                            getStudentList.add(String.valueOf(studentID));


                            countStudents++;

                            }
                        }
                    }

                    else if (moduleID8.equals("0") && !moduleID7.equals("0")) {

                        for (i = 0; i < assignedModules; i++) {
                            if (moduleCodesArray[i].equals(moduleID1) || moduleCodesArray[i].equals(moduleID2) || moduleCodesArray[i].equals(moduleID3) || moduleCodesArray[i].equals(moduleID4) || moduleCodesArray[i].equals(moduleID5) || moduleCodesArray[i].equals(moduleID6) || moduleCodesArray[i].equals(moduleID7)) {

                                System.out.println("|    " + moduleCodesArray[i] + "\t\t\t\t|\t\t\t\t" + studentID + "\t\t   |\n");

                                moduleCodesInList.add(moduleCodesArray[i]);
                                moduleCodesInSet.add(moduleCodesArray[i]);
                                getStudentList.add(String.valueOf(studentID));

                                countStudents++;
                            }
                        }
                    }

                    else if (!moduleID7.equals("0") && !moduleID8.equals("0")) {

                        for (i = 0; i < assignedModules; i++) {
                            if (moduleCodesArray[i].equals(moduleID1) || moduleCodesArray[i].equals(moduleID2) || moduleCodesArray[i].equals(moduleID3) || moduleCodesArray[i].equals(moduleID4) || moduleCodesArray[i].equals(moduleID5) || moduleCodesArray[i].equals(moduleID6) || moduleCodesArray[i].equals(moduleID7) || moduleCodesArray[i].equals(moduleID8)) {

                                System.out.println("|    " + moduleCodesArray[i] + "\t\t\t\t|\t\t\t\t" + studentID + "\t\t   |\n");

                                moduleCodesInList.add(moduleCodesArray[i]);
                                moduleCodesInSet.add(moduleCodesArray[i]);
                                getStudentList.add(String.valueOf(studentID));

                                countStudents++;
                            }
                        }
                    }
                }

                System.out.print("--------------------------------------------------------");

                bR.close();
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found for student records.\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of student records\n");
            }

        }

        return countStudents;
    }




    // method for instructors (module leaders) to add marks to each students assigned, in each module of a particular course ID
    private void addModuleMarks(String instructorName, String fileName, int level){

        String grade = "N/A";

        boolean errorCaught = true;


        System.out.println("\n----------------Allocate Module Marks----------------");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter module code to add marks to: ");
        String inputModuleCode = scan.next();
        System.out.println("Enter student ID to add the marks to: ");
        int inputStudentID = scan.nextInt();


        if (level == 4 || level == 5) {
            if (instructorsName.equalsIgnoreCase(instructorName) && assignedModuleCode.contains(inputModuleCode)) {
                if (studentListInModule.contains(String.valueOf(inputStudentID))) {

                    System.out.println("Enter marks: ");
                    int studentMarks = scan.nextInt();

                    System.out.println("\n----------------Display Module Marks----------------");
                    System.out.println("Registered Student ID: " + inputStudentID);
                    System.out.println("Registered Course ID: " + courseIDAssigned);
                    System.out.println("Registered Module Code: " + inputModuleCode);
                    System.out.println("Module Marks: " + studentMarks);

                    if (studentMarks >= 70 && studentMarks <= 100) {
                        grade = "A";
                        System.out.println("Result: " + grade);
                    } else if(studentMarks >= 60 && studentMarks <= 69) {
                        grade = "B";
                        System.out.println("Result: " + grade);
                    } else if(studentMarks >= 54 && studentMarks <= 59){
                        grade = "C";
                        System.out.println("Result: " + grade);
                    }else if(studentMarks >= 42 && studentMarks <= 53){
                        grade = "D";
                        System.out.println("Result: " + grade);
                    }else if(studentMarks >= 38 && studentMarks <= 41){
                        grade = "E";
                        System.out.println("Result: " + grade);
                    }else if(studentMarks >= 0 && studentMarks <= 37){
                        grade = "F";
                        System.out.println("Result: " + grade);
                    }

                    errorCaught = false;

                    if(level == 4) {
                        try{
                            File fp = new File("Student_Level_4_Result.txt");
                            if(fp.createNewFile()){
                                System.out.println("\nFile created for storing student's level 4 modules results...\n");
                            }

                            else{
                                System.out.println("\nStudent level 4 modules results file already exists...\n");
                            }
                        }
                        catch(IOException e){
                            System.out.println("\nError creating the file\n");
                        }

                        try {
                            FileWriter fW = new FileWriter("Student_Level_4_Result.txt", true);
                            fW.write(inputStudentID + "\t" + courseIDAssigned + "\t" + inputModuleCode + "\t" + studentMarks + "\t" + grade + "\t" + level);
                            fW.write("\n");
                            fW.close();

                        } catch (IOException e) {
                            System.out.println("\nError writing into the file\n");
                        }
                    }

                    else {
                        try{
                            File fp = new File("Student_Level_5_Result.txt");
                            if(fp.createNewFile()){
                                System.out.println("\nFile created for storing student's level 5 modules results...\n");
                            }

                            else{
                                System.out.println("\nStudent level 5 modules results file already exists...\n");
                            }
                        }
                        catch(IOException e){
                            System.out.println("\nError creating the file\n");
                        }

                        try {
                            FileWriter fW = new FileWriter("Student_Level_5_Result.txt", true);
                            fW.write(inputStudentID + "\t" + courseIDAssigned + "\t" + inputModuleCode + "\t" + studentMarks + "\t" + grade + "\t" + level);
                            fW.write("\n");
                            fW.close();
                        } catch (IOException e) {
                            System.out.println("\nError writing into the file\n");
                        }
                    }

                }
            }

            studentListInModule.clear();
        }




        else if(level == 6) {
            if (instructorsNameLevel6.equalsIgnoreCase(instructorName) && assignedModuleCode.contains(inputModuleCode)) {
                if (getStudentList.contains(String.valueOf(inputStudentID))) {

                    System.out.println("Enter marks: ");
                    int studentMarks = scan.nextInt();

                    System.out.println("\n----------------Display Module Marks----------------");
                    System.out.println("Registered Student ID: " + inputStudentID);
                    System.out.println("Registered Course ID: " + courseIDAssigned);
                    System.out.println("Registered Module Code: " + inputModuleCode);
                    System.out.println("Module Marks: " + studentMarks);

                    if (studentMarks >= 70 && studentMarks <= 100) {
                        grade = "A";
                        System.out.println("Result: " + grade);
                    } else if(studentMarks >= 60 && studentMarks <= 69) {
                        grade = "B";
                        System.out.println("Result: " + grade);
                    } else if(studentMarks >= 54 && studentMarks <= 59){
                        grade = "C";
                        System.out.println("Result: " + grade);
                    }else if(studentMarks >= 42 && studentMarks <= 53){
                        grade = "D";
                        System.out.println("Result: " + grade);
                    }else if(studentMarks >= 38 && studentMarks <= 41){
                        grade = "E";
                        System.out.println("Result: " + grade);
                    }else if(studentMarks >= 0 && studentMarks <= 37){
                        grade = "F";
                        System.out.println("Result: " + grade);
                    }

                    errorCaught = false;

                    try{
                        File fp = new File("Student_Level_6_Result.txt");
                        if(fp.createNewFile()){
                            System.out.println("\nFile created for storing student's level 6 modules results...\n");
                        }

                        else{
                            System.out.println("\nStudent level 6 modules results file already exists...\n");
                        }
                    }
                    catch(IOException e){
                        System.out.println("\nError creating the file\n");
                    }

                    try {
                        FileWriter fW = new FileWriter("Student_Level_6_Result.txt", true);
                        fW.write(inputStudentID + "\t" + courseIDAssigned + "\t" + inputModuleCode + "\t" + studentMarks + "\t" + grade + "\t" + level);
                        fW.write("\n");
                        fW.close();
                    } catch (IOException e) {
                        System.out.println("\nError writing into the file\n");
                    }

                }

            }

            getStudentList.clear();
        }


        if (errorCaught) {
            System.out.println("\nEither instructor's name, module code, or student ID did not match\n");
        }

    }



    // user interface for instructors
    public void instructorProgramInterface(){
        String instructorName;

        boolean isInstructorFoundInLevel4, isInstructorFoundInLevel5, isInstructorFoundInLevel6;
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\t1. View assigned modules for level 4, 5 or 6");
        System.out.println("\t\t\t2. View students registered & add marks to the modules assigned");
        System.out.println("\t\t\t3. Return to main-menu");
        System.out.println("\t\t\t4. Exit");

        System.out.println("\nSelect an option: ");
        int instructorOption = sc.nextInt();
        sc.nextLine();


            switch(instructorOption){
                case 1:
                    System.out.println("\nInstructor, enter your name: ");
                    instructorName = sc.nextLine();
                    isInstructorFoundInLevel4 = assignedModules(instructorName, "Level_4_Modules.txt", 4, true, false);
                    isInstructorFoundInLevel5 = assignedModules(instructorName, "Level_5_Modules.txt", 5, true, false);
                    isInstructorFoundInLevel6 = assignedModules(instructorName, "Level_6_Modules.txt", 6, true, false);

                    // if the input instructor name does not exist in either of the three levels, user is informed with the message
                    // displaying that the instructor does not exist
                    if(!isInstructorFoundInLevel4 && !isInstructorFoundInLevel5 && !isInstructorFoundInLevel6){
                        System.out.println("\nSorry! The input instructor name does not exist");
                    }

                    System.out.println("\nLogged in as instructor...");
                    instructorProgramInterface();

                case 2:
                    System.out.println("\nInstructor, enter your name: ");
                    instructorName = sc.nextLine();
                    isInstructorFoundInLevel4 = assignedModules(instructorName, "Level_4_Modules.txt", 4, false, true);
                    getIndexOf = 0;
                    moduleCodesInList.clear();
                    studentRecordsList.clear();
                    isInstructorFoundInLevel5 = assignedModules(instructorName, "Level_5_Modules.txt", 5, false, true);
                    getIndexOf = 0;
                    moduleCodesInList.clear();
                    studentRecordsList.clear();
                    isInstructorFoundInLevel6 = assignedModules(instructorName, "Level_6_Modules.txt", 6, false, true);
                    getIndexOf = 0;
                    moduleCodesInList.clear();
                    studentRecordsList.clear();

                    if(!isInstructorFoundInLevel4 && !isInstructorFoundInLevel5 && !isInstructorFoundInLevel6){
                        System.out.println("\nSorry! The input instructor name does not exist");
                    }

                    System.out.println("\nLogged in as instructor...");
                    instructorProgramInterface();

                case 3:
                    System.out.print("\n");
                    mainInterface();

                case 4:
                    System.exit(0);

                default:
                    System.out.println("\nSorry! the entered option does not exist");
            }

    }


    public static void main(String[] args) {
        Instructor instructor = new Instructor();

        System.out.println("-------------------------------------------Welcome to Course Registration System-------------------------------------------");
        instructor.mainInterface();

    }
}
