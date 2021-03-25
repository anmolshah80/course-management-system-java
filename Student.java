package CourseManagementSystem;

import java.io.*;
import java.util.*;



public class Student extends CourseAdministrator {

    private String studentName;
    private String studentID;
    private String emailAddress;


    public List<Integer> studentModuleMarksInList = new ArrayList<>();
    public List<String> studentModuleGradesInList = new ArrayList<>();



    // attribute studentAccountList created for adding the objects of Student class into an ArrayList
    public ArrayList<String> studentDetailsList = new ArrayList<>();

    // method created to add the objects in the list of Student
    public void addNewStudentRecords(){
        this.studentDetailsList.add(studentID);
        this.studentDetailsList.add(studentName);
        this.studentDetailsList.add(emailAddress);
        this.studentDetailsList.add(String.valueOf(courseID));
        this.studentDetailsList.add(courseName);
    }

    // arraylist to add module details from the files
    public ArrayList<String> moduleDetailsList = new ArrayList<>();

    // method to add module details in the arraylist and display purposes
    public void addStudentModuleDetailsInList(int modulesAssigned){
        this.moduleDetailsList.add(studentID);
        this.moduleDetailsList.add(String.valueOf(courseID));

        if(modulesAssigned <= 8){
            this.moduleDetailsList.add(moduleID1);
            this.moduleDetailsList.add(moduleID2);
            this.moduleDetailsList.add(moduleID3);
            this.moduleDetailsList.add(moduleID4);
            this.moduleDetailsList.add(moduleID5);
            this.moduleDetailsList.add(moduleID6);
            this.moduleDetailsList.add(moduleID7);
            this.moduleDetailsList.add(moduleID8);
        }

        else{
            System.out.println("\nSorry! cannot assign " + modulesAssigned + " modules in a course level");
        }
    }



    // getter and setter methods to store and manipulate student information
    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getModuleID1() {
        return moduleID1;
    }

    public void setModuleID1(String moduleID1) {
        this.moduleID1 = moduleID1;
    }

    public String getModuleID2() {
        return moduleID2;
    }

    public void setModuleID2(String moduleID2) {
        this.moduleID2 = moduleID2;
    }

    public String getModuleID3() {
        return moduleID3;
    }

    public void setModuleID3(String moduleID3) {
        this.moduleID3 = moduleID3;
    }

    public String getModuleID4() {
        return moduleID4;
    }

    public void setModuleID4(String moduleID4) {
        this.moduleID4 = moduleID4;
    }

    public String getModuleID5() {
        return moduleID5;
    }

    public void setModuleID5(String moduleID5) {
        this.moduleID5 = moduleID5;
    }

    public String getModuleID6() {
        return moduleID6;
    }

    public void setModuleID6(String moduleID6) {
        this.moduleID6 = moduleID6;
    }

    public String getModuleID7() {
        return moduleID7;
    }

    public void setModuleID7(String moduleID7) {
        this.moduleID7 = moduleID7;
    }

    public String getModuleID8() {
        return moduleID8;
    }

    public void setModuleID8(String moduleID8) {
        this.moduleID8 = moduleID8;
    }



    // method created to add a new student record
    public void addNewStudentDetails(String fileName){

        boolean isOptionValid = true;
        String[] studentDetails;
        boolean studentIDExists = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------Add your details----------------");
        System.out.println("Enter your name: ");
        String studentName = sc.nextLine();
        System.out.println("Enter your ID: ");
        String studentID = sc.nextLine();
        System.out.println("Enter your email address: ");
        String emailAddress = sc.nextLine();


        String record;
        int countRecords = 0;

        String[] courseIDInFile = new String[100];
        String[] courseNameInFile = new String[100];
        int i = 1;

        try {
            File inputFile = new File(fileName);

            BufferedReader bR = new BufferedReader(new FileReader(inputFile));

            System.out.println("\n----------------Available Courses----------------");

            while ((record = bR.readLine()) != null) {
                countRecords += 1;

                StringTokenizer st = new StringTokenizer(record, "\t");

                courseIDInFile[i] = st.nextToken();
                courseNameInFile[i] = st.nextToken();

                System.out.println(countRecords + "." + " Course ID: " + courseIDInFile[i] + ", " + "Course Name: " + courseNameInFile[i]);
                i++;
            }

            bR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found for course records.\n");
        } catch (IOException e) {
            System.out.println("\nError reading the file contents of course records\n");
        }


        System.out.println("\n----------------Register for a Course----------------");
        System.out.println("Select a course: ");
        int courseSelected = sc.nextInt();

        if(courseSelected <= 0 || courseSelected > countRecords){
            System.out.println("\nSorry! The selected option for the course does not exist...");
            isOptionValid = false;
        }

        else{
            setStudentName(studentName);
            setStudentID(studentID);
            setEmailAddress(emailAddress);
            setCourseID(Integer.parseInt(courseIDInFile[courseSelected]));
            setCourseName(courseNameInFile[courseSelected]);


            boolean exceptionsCaught = false;
            try{
                File fp1 = new File("Student_Records.txt");

                if(fp1.createNewFile()){
                    System.out.println("\nFile created to add student details.\n");
                }
                else{
                    System.out.println("\nStudent details file already exists.\n");

                    try{
                        FileReader fR = new FileReader("Student_Records.txt");

                        Scanner scan = new Scanner(fR);

                        while(scan.hasNextLine()){
                            String studentRecords = scan.nextLine();
                            studentDetails = studentRecords.split("\t");

                            studentIDInSet.add(Integer.parseInt(studentDetails[0]));
                        }

                        fR.close();

                    }

                    catch(IOException e){
                        System.out.println("\nError reading student file records.");
                    }
                }
            }

            catch(IOException e){
                System.out.println("\nError creating the file.\n");
            }

            try{
                addNewStudentRecords();
                FileWriter fW = new FileWriter("Student_Records.txt", true);

                Iterator itr = studentDetailsList.iterator();
                while(itr.hasNext()){
                    String listData = (String)itr.next();
                    if(studentIDInSet.contains(Integer.parseInt(getStudentID()))){
                        studentIDExists = true;
                        continue;
                    }
                    fW.write(listData + "\t");
                }

                if(!studentIDExists){
                    fW.write("\n");
                }

                fW.close();

                studentDetailsList.clear();
            }

            catch(IOException e){
                System.out.println("Error writing into the file.\n");
                exceptionsCaught = true;
            }

            if(!exceptionsCaught){
                System.out.println("\nStudent details added successfully...\n");
            }
        }


        if(isOptionValid) {
            int getStudentID = Integer.parseInt(getStudentID());

            // calling the method to assign students to level 4 modules
            assignLevel4And5Modules(getStudentID, Integer.parseInt(courseIDInFile[courseSelected]), 4);

            // calling the method to assign students to level 5 modules
            assignLevel4And5Modules(getStudentID, Integer.parseInt(courseIDInFile[courseSelected]), 5);

            // method to let student add modules of their choice
            chooseModules(Integer.parseInt(courseIDInFile[courseSelected]), 6);
        }
    }



    // method to read and display level 6 module details
    private void level6ModulesDisplay(int validCourseID){
        String[] moduleDetails;
        int courseID;
        String courseName;
        String moduleCode;
        String moduleName;
        int moduleSemester;
        String moduleType;
        int serialNum = 1;

        boolean isValidCourseID = false;


        try{
            FileReader level6ModuleDetails = new FileReader("Level_6_Modules.txt");
            Scanner sc = new Scanner(level6ModuleDetails);

            while(sc.hasNextLine()){
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");

                courseID = Integer.parseInt(moduleDetails[0]);
                courseName = moduleDetails[1];
                moduleCode = moduleDetails[2];
                moduleName = moduleDetails[3];
                moduleSemester = Integer.parseInt(moduleDetails[6]);
                moduleType = moduleDetails[7];

                if(validCourseID == courseID){
                    if(serialNum == 1){
                        System.out.println("\n----------------" + courseID + " " + courseName + " Level 6 Modules----------------");
                    }


                    System.out.println(serialNum + ". " + moduleCode + ": " + moduleName + " - " + "Sem " + moduleSemester + " (" + moduleType + ")");
                    System.out.print("\n");


                    isValidCourseID = true;
                    serialNum++;
                }

            }

            level6ModuleDetails.close();

            if(!isValidCourseID){
                System.out.println("\nSorry! Modules for the entered course ID does not exist in level 6 modules file");
            }

        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 6 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 6 modules\n");
        }
    }


    // method to read mandatory modules present in level 6 modules file
    private int readMandatoryModules(int courseID){
        String record;
        int countRecord = 0;
        int n = 1, o = 1;

        String[] courseIDInFile = new String[100];
        String[] courseNameInFile = new String[100];
        String[] moduleCodeInFile = new String[100];
        String[] moduleNameInFile = new String[100];
        String[] moduleLeaderInFile = new String[100];
        int[] moduleYearInFile = new int[100];
        int[] moduleSemesterInFile = new int[100];
        String[] moduleTypeInFile = new String[100];


        try{
            File inputFile = new File("Level_6_Modules.txt");

            BufferedReader bR = new BufferedReader(new FileReader(inputFile));


            while ((record = bR.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(record, "\t");

                courseIDInFile[n] = st.nextToken();
                courseNameInFile[n] = st.nextToken();
                moduleCodeInFile[n] = st.nextToken();
                moduleNameInFile[n] = st.nextToken();
                moduleLeaderInFile[n] = st.nextToken();
                moduleYearInFile[n] = Integer.parseInt(st.nextToken());
                moduleSemesterInFile[n] = Integer.parseInt(st.nextToken());
                moduleTypeInFile[n] = st.nextToken();

                if(moduleTypeInFile[n].equals("mandatory") && courseIDInFile[n].equals(String.valueOf(courseID))){
                    moduleCodeList[o] = moduleCodeInFile[n];
                    System.out.println(o + ". " + moduleCodeInFile[n] + ": " + moduleNameInFile[n] + " - " + "Sem " + moduleSemesterInFile[n] + " (" + moduleTypeInFile[n] + ")");
                    o++;
                    countRecord++;
                }

                n++;
            }

            bR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found for level 6 module records.\n");
        } catch (IOException e) {
            System.out.println("\nError reading the file contents of level 6 module records\n");
        }


        return countRecord;
    }



    // method for students to choose level 6 optional modules
    private void chooseModules(int courseID, int level){
        Scanner sc = new Scanner(System.in);

        String firstOptionalModule, secondOptionalModule;
        int modulesAssigned = 1;
        boolean errorFound = false;

        if(level == 4 || level == 5){
            System.out.println("Sorry! you cannot choose modules at level " + level);
        }


        else {
            System.out.println("\n----------------Available modules for Level 6----------------");
            level6ModulesDisplay(courseID);

            String currentRecord;
            int countRecords = 0;

            String[] courseIDInFile = new String[100];
            String[] courseNameInFile = new String[100];
            String[] moduleCodeInFile = new String[100];
            String[] moduleNameInFile = new String[100];
            String[] moduleLeaderInFile = new String[100];
            int[] moduleYearInFile = new int[100];
            int[] moduleSemesterInFile = new int[100];
            String[] moduleTypeInFile = new String[100];

            int i = 1;

            try {
                File inputFile = new File("Level_6_Modules.txt");

                BufferedReader bR = new BufferedReader(new FileReader(inputFile));

                System.out.println("\n----------------Available Optional Modules----------------");

                while ((currentRecord = bR.readLine()) != null) {
                    countRecords += 1;

                    StringTokenizer st = new StringTokenizer(currentRecord, "\t");


                    courseIDInFile[i] = st.nextToken();
                    courseNameInFile[i] = st.nextToken();
                    moduleCodeInFile[i] = st.nextToken();
                    moduleNameInFile[i] = st.nextToken();
                    moduleLeaderInFile[i] = st.nextToken();
                    moduleYearInFile[i] = Integer.parseInt(st.nextToken());
                    moduleSemesterInFile[i] = Integer.parseInt(st.nextToken());
                    moduleTypeInFile[i] = st.nextToken();


                    if (moduleTypeInFile[i].equals("optional") && courseIDInFile[i].equals(String.valueOf(courseID))) {
                        System.out.println(i + ". " + moduleCodeInFile[i] + ": " + moduleNameInFile[i] + " - " + "Sem " + moduleSemesterInFile[i] + " (" + moduleTypeInFile[i] + ")");
                    }

                    i++;
                }

                bR.close();
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found for course records.\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of course records\n");
            }


            System.out.println("\n\n----------------Choose optional modules for Level 6----------------");
            System.out.println("Select either two, one, or none of the optional modules available using the index displayed under 'Available Optional Modules'");
            System.out.println("\n----> To select two modules, for example modules 6 and 8: enter '6' in the first input field and '8' in the second input field, without quotes");
            System.out.println("----> To select only one module, for example module 10: enter '10', in the first input field and then enter '0' in the second input field, without quotes or vice versa");
            System.out.println("----> To select none of the optional modules: enter '0' without quotes on both input fields");

            System.out.println("\nSelect first optional module: ");
            firstOptionalModule = sc.nextLine();
            System.out.println("\nSelect second optional module: ");
            secondOptionalModule = sc.nextLine();


            int firstOptionModule = Integer.parseInt(firstOptionalModule);
            int secondOptionModule = Integer.parseInt(secondOptionalModule);

            if (firstOptionModule < 0 || firstOptionModule > countRecords) {
                System.out.println("\nSorry! The selected option for the first optional module does not exist");
                errorFound = true;
            }

            else if (secondOptionModule < 0 || secondOptionModule > countRecords) {
                System.out.println("\nSorry! The selected option for the second optional module does not exist");
                errorFound = true;
            }

            else if (!errorFound) {

                    // if none of the optional modules are selected
                    if (firstOptionModule == 0 && secondOptionModule == 0) {
                        System.out.println("\n----------------Assigned Modules for Level 6----------------");
                        int o = readMandatoryModules(courseID);

                        o++;
                        setModuleID7("0");

                        o++;
                        setModuleID8("0");
                        modulesAssigned = o;
                    }


                    // if one of the optional modules is selected
                    else if (firstOptionModule == 0 && secondOptionModule != 0) {
                        System.out.println("\n----------------Assigned Modules for Level 6----------------");
                        int o = readMandatoryModules(courseID);


                        if (moduleTypeInFile[secondOptionModule].equals("optional")) {
                            o++;
                            setModuleID7(moduleCodeInFile[secondOptionModule]);
                            System.out.println(o + ". " + moduleCodeInFile[secondOptionModule] + ": " + moduleNameInFile[secondOptionModule] + " - " + "Sem " + moduleSemesterInFile[secondOptionModule] + " (" + moduleTypeInFile[secondOptionModule] + ")");

                            o++;
                            setModuleID8("0");
                            modulesAssigned = o;
                        }

                        else if (moduleTypeInFile[secondOptionModule].equals("mandatory")){
                            System.out.println("\nSorry! Mandatory modules cannot be selected as optional modules");
                            o++;
                            setModuleID7("0");
                            o++;
                            setModuleID8("0");
                            modulesAssigned = o;
                        }
                    }


                    // if one of the optional modules is selected
                    else if (secondOptionModule == 0 && firstOptionModule != 0) {
                        System.out.println("\n----------------Assigned Modules for Level 6----------------");
                        int o = readMandatoryModules(courseID);


                        if (moduleTypeInFile[firstOptionModule].equals("optional")) {
                            o++;
                            setModuleID7(moduleCodeInFile[firstOptionModule]);
                            System.out.println(o + ". " + moduleCodeInFile[firstOptionModule] + ": " + moduleNameInFile[firstOptionModule] + " - " + "Sem " + moduleSemesterInFile[firstOptionModule] + " (" + moduleTypeInFile[firstOptionModule] + ")");

                            o++;
                            setModuleID8("0");
                            modulesAssigned = o;
                        }

                        else if (moduleTypeInFile[firstOptionModule].equals("mandatory")){
                            System.out.println("\nSorry! Mandatory modules cannot be selected as optional modules");
                            o++;
                            setModuleID7("0");
                            o++;
                            setModuleID8("0");
                            modulesAssigned = o;
                        }
                    }


                    // if two optional modules are selected from the available optional modules
                    else if (firstOptionModule != 0 && secondOptionModule != 0) {
                        System.out.println("\n----------------Assigned Modules for Level 6----------------");


                        int o = readMandatoryModules(courseID);



                        if (moduleTypeInFile[firstOptionModule].equals("optional")) {
                            o++;
                            setModuleID7(moduleCodeInFile[firstOptionModule]);
                            System.out.println(o + ". " + moduleCodeInFile[firstOptionModule] + ": " + moduleNameInFile[firstOptionModule] + " - " + "Sem " + moduleSemesterInFile[firstOptionModule] + " (" + moduleTypeInFile[firstOptionModule] + ")");
                        }

                        if (moduleTypeInFile[secondOptionModule].equals("optional")) {
                            o++;
                            setModuleID8(moduleCodeInFile[secondOptionModule]);
                            System.out.println(o + ". " + moduleCodeInFile[secondOptionModule] + ": " + moduleNameInFile[secondOptionModule] + " - " + "Sem " + moduleSemesterInFile[secondOptionModule] + " (" + moduleTypeInFile[secondOptionModule] + ")");
                            modulesAssigned = o;
                        }

                        else if(moduleTypeInFile[firstOptionModule].equals("mandatory") && moduleTypeInFile[secondOptionModule].equals("mandatory")){
                            System.out.println("\nSorry! Mandatory modules cannot be selected as optional modules");
                            o++;
                            setModuleID7("0");
                            o++;
                            setModuleID8("0");
                            modulesAssigned = o;
                        }
                    }
            }


            if (!errorFound) {
                // for loop to set modules selected by a student to the respective module IDs which were temporarily
                // stored in the 'moduleCodeList' string array
                for (int j = 1; j < moduleCodeList.length; j++) {
                    if (j == 1) {
                        setModuleID1(moduleCodeList[j]);
                    } else if (j == 2) {
                        setModuleID2(moduleCodeList[j]);
                    } else if (j == 3) {
                        setModuleID3(moduleCodeList[j]);
                    } else if (j == 4) {
                        setModuleID4(moduleCodeList[j]);
                    } else if (j == 5) {
                        setModuleID5(moduleCodeList[j]);
                    } else if (j == 6) {
                        setModuleID6(moduleCodeList[j]);
                    }
                }


                boolean exceptionsCaught = false;
                try {
                    File fp = new File("Student_Module_6_Records.txt");

                    if (fp.createNewFile()) {
                        System.out.println("\nFile created to store student module details...\n");
                    } else {
                        System.out.println("\nStudent module records file already exists...\n");
                    }

                } catch (IOException e) {
                    System.out.println("\nError creating the file.\n");
                }

                try {
                    addStudentModuleDetailsInList(modulesAssigned);

                    FileWriter fW = new FileWriter("Student_Module_6_Records.txt", true);

                    Iterator itr = moduleDetailsList.iterator();

                    while (itr.hasNext()) {
                        String listData = (String) itr.next();
                        fW.write(listData + "\t");
                    }
                    fW.write("\n");
                    fW.close();

                    moduleDetailsList.clear();
                } catch (IOException e) {
                    System.out.println("Error writing into the file.\n");
                    exceptionsCaught = true;
                }

                if (!exceptionsCaught) {
                    System.out.println("\nStudent module details added successfully\n");
                }
            }

        }
    }



    // method to display the student's personal and module details
    private boolean displayStudentDetails(String studentDetailsFile, String studentModulesFile){
        String[] studentRecords;
        String[] moduleRecords;
        int studentID;
        String studentName;
        String emailAddress;
        int courseID;
        String courseName;


        boolean isStudentIDValid = false;
        boolean modulesFound = false;


        System.out.println("\n----------------View Modules Assigned for Level 6----------------");
        System.out.println("Enter student ID: ");
        Scanner scan = new Scanner(System.in);
        int inputStudentID = scan.nextInt();



        // to display student's personal details
        try {
            FileReader fR = new FileReader(studentDetailsFile);
            Scanner sc = new Scanner(fR);


            while (sc.hasNextLine()) {
                String studentDetails = sc.nextLine();
                studentRecords = studentDetails.split("\t");

                studentID = Integer.parseInt(studentRecords[0]);
                studentName = studentRecords[1];
                emailAddress = studentRecords[2];
                courseID = Integer.parseInt(studentRecords[3]);
                courseName = studentRecords[4];


                if (inputStudentID == studentID) {
                    // displaying the student personal details
                    System.out.println("\n----------------Student Details----------------");
                    System.out.println("Student ID: " + studentID);
                    System.out.println("Student Name: " + studentName);
                    System.out.println("Email Address: " + emailAddress);
                    System.out.println("Course ID: " + courseID);
                    System.out.println("Course Name: " + courseName);
                    isStudentIDValid = true;
                }

            }

            fR.close();

            if(!isStudentIDValid){
                System.out.println("\nError! Input student ID not found\n");
            }
        }


        catch(FileNotFoundException e){
            System.out.println("\nFile not found for student records.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of student records\n");
        }



        // to display student's module details
        try {
            FileReader fR = new FileReader(studentModulesFile);
            Scanner sc = new Scanner(fR);


            while (sc.hasNextLine()) {
                String moduleDetails = sc.nextLine();
                moduleRecords = moduleDetails.split("\t");

                studentID = Integer.parseInt(moduleRecords[0]);
                moduleID1 = moduleRecords[2];
                moduleID2 = moduleRecords[3];
                moduleID3 = moduleRecords[4];
                moduleID4 = moduleRecords[5];
                moduleID5 = moduleRecords[6];
                moduleID6 = moduleRecords[7];
                moduleID7 = moduleRecords[8];
                moduleID8 = moduleRecords[9];

                setModuleID1(moduleID1);
                setModuleID2(moduleID2);
                setModuleID3(moduleID3);
                setModuleID4(moduleID4);
                setModuleID5(moduleID5);
                setModuleID6(moduleID6);


                if(studentID == inputStudentID && isStudentIDValid) {
                    // check if that particular student has only taken mandatory modules
                    // or added any optional modules as well
                    System.out.println("Assigned module codes: ");
                    if (!moduleID7.equals("0") && !moduleID8.equals("0")) {
                        setModuleID7(moduleID7);
                        setModuleID8(moduleID8);
                        System.out.print(getModuleID1() + ", " + getModuleID2() + ", " + getModuleID3() + ", " + getModuleID4() + ", " + getModuleID5() + ", " + getModuleID6() + ", " + getModuleID7() + ", " + getModuleID8());
                        System.out.println("\n");
                    } else if (!moduleID7.equals("0") && moduleID8.equals("0")) {
                        setModuleID7(moduleID7);
                        System.out.print(getModuleID1() + ", " + getModuleID2() + ", " + getModuleID3() + ", " + getModuleID4() + ", " + getModuleID5() + ", " + getModuleID6() + ", " + getModuleID7());
                        System.out.println("\n");
                    } else {
                        System.out.print(getModuleID1() + ", " + getModuleID2() + ", " + getModuleID3() + ", " + getModuleID4() + ", " + getModuleID5() + ", " + getModuleID6());
                        System.out.println("\n");
                    }

                    modulesFound = true;
                }

            }

            if(!modulesFound) {
                System.out.println("\nError! Module details not found for the input student ID\n");
            }
            fR.close();
        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for student records.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of student records\n");
        }

        return isStudentIDValid;

    }



    // method to display instructors assigned for the modules (in all three levels) for a specific course
    public void viewInstructorsForSelectedCourse(int inputCourseID){
        String[] moduleDetails;
        int courseID;
        String moduleCode;
        String moduleName;
        String moduleLeader;
        int index = 1;


        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("|    " + "S.N.\t|" + "\t\tInstructor Name\t\t|" + "\tModule Code\t|" + "\t\t\t\t\t\tModule Name\t\t\t\t\t" + "      |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        try{
            FileReader fR = new FileReader("Level_4_Modules.txt");
            Scanner sc = new Scanner(fR);


            while(sc.hasNextLine()){
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");

                courseID = Integer.parseInt(moduleDetails[0]);
                moduleCode = moduleDetails[2];
                moduleName = moduleDetails[3];
                moduleLeader = moduleDetails[4];



                if(inputCourseID == courseID) {

                    System.out.format("|    %2d. \t|\t%20s\t|\t%6s\t\t|\t%50s    |\n", index, moduleLeader, moduleCode, moduleName);
                    System.out.print("\n");

                    index++;
                }
            }

            fR.close();


        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 4 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 4 modules\n");
        }


        try{
            FileReader fR = new FileReader("Level_5_Modules.txt");
            Scanner sc = new Scanner(fR);

            while(sc.hasNextLine()){
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");

                courseID = Integer.parseInt(moduleDetails[0]);
                moduleCode = moduleDetails[2];
                moduleName = moduleDetails[3];
                moduleLeader = moduleDetails[4];


                if(inputCourseID == courseID) {
                    System.out.format("|    %2d. \t|\t%20s\t|\t%6s\t\t|\t%50s    |\n", index, moduleLeader, moduleCode, moduleName);
                    System.out.print("\n");

                    index++;
                }
            }
            fR.close();

        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 5 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 5 modules\n");
        }


        try{
            FileReader fR = new FileReader("Level_6_Modules.txt");
            Scanner sc = new Scanner(fR);

            while(sc.hasNextLine()){
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");


                courseID = Integer.parseInt(moduleDetails[0]);
                moduleCode = moduleDetails[2];
                moduleName = moduleDetails[3];
                moduleLeader = moduleDetails[4];

                if(inputCourseID == courseID) {
                    System.out.format("|    %2d. \t|\t%20s\t|\t%6s\t\t|\t%50s    |\n", index, moduleLeader, moduleCode, moduleName);
                    System.out.print("\n");

                    index++;
                }

            }

            fR.close();
        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 6 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 6 modules\n");
        }

        System.out.print("-------------------------------------------------------------------------------------------------------------------");
    }




    // method to print out the assigned/registered module marks of a student from the files,
    // across all three levels (if marks for that module or level exists in the file)
    public void displayResultSlip(int inputStudentID, int inputLevel){

        String[] records;
        String[] moduleDetails;

        int studentID;
        int hasStudentMarks = 0;
        int failedModules = 0;



        // read student personal records
        try {
            FileReader fR = new FileReader("Student_Records.txt");
            Scanner sc = new Scanner(fR);


            while (sc.hasNextLine()) {
                String studentDetails = sc.nextLine();
                records = studentDetails.split("\t");

                if (records[0].equals(String.valueOf(inputStudentID))) {
                    setStudentID(records[0]);
                    setStudentName(records[1]);
                    setEmailAddress(records[2]);
                    setCourseID(Integer.parseInt(records[3]));
                    setCourseName(records[4]);
                }

            }
            fR.close();
        } catch (IOException e) {
            System.out.println("\nCould read the records of student\n");
        }


        // reading the student result file for the modules registered in either of the levels
        try{
            FileReader fR = new FileReader("Student_Level_4_Result.txt");

            if(inputLevel == 5){
                fR = new FileReader("Student_Level_5_Result.txt");
            }

            else if(inputLevel == 6){
                fR = new FileReader("Student_Level_6_Result.txt");
            }

            Scanner sc = new Scanner(fR);

            while(sc.hasNextLine()){
                String results = sc.nextLine();
                moduleDetails = results.split("\t");

                studentID = Integer.parseInt(moduleDetails[0]);

                if(studentID == inputStudentID){

                    if(Integer.parseInt(moduleDetails[3]) < 40){
                        failedModules++;
                    }


                    assignedModuleCode.add(moduleDetails[2]);
                    studentModuleMarksInList.add(Integer.parseInt(moduleDetails[3]));
                    studentModuleGradesInList.add(moduleDetails[4]);

                    hasStudentMarks++;

                }


            }

            fR.close();
        }

        catch (IOException e){
            System.out.println("\nCould not read module results file");
        }




        if(hasStudentMarks > 0){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("|    " + "\t\t\t\tSTATEMENT OF RESULTS\t\t\t\t" + "\t    |");
            System.out.println("-----------------------------------------------------------------");
            System.out.format("|    Student ID    :    %20s\t\t\t\t    |\n", getStudentID());
            System.out.format("|    Student Name  :    %20s\t\t\t\t    |\n", getStudentName());
            System.out.format("|    Course ID     :    %20d\t\t\t\t    |\n", getCourseID());
            System.out.format("|    Course Name   :    %20s\t\t\t\t    |\n", getCourseName());
            System.out.format("|    Level         :    %20d\t\t\t\t    |\n", inputLevel);
            System.out.println("-----------------------------------------------------------------");
            System.out.println("|    Module Code\t\t\t|\tMarks\t\t\t|\tGrade\t    |");
            System.out.println("-----------------------------------------------------------------");

            if(inputLevel == 4 || inputLevel == 5){
                for (int i = 0; i < assignedModuleCode.size(); i++) {
                    System.out.format("|    %6s\t\t\t\t\t|\t%4d\t\t\t|\t%3s\t\t    |\n", assignedModuleCode.get(i), studentModuleMarksInList.get(i), studentModuleGradesInList.get(i));
                }
            }

            else if(inputLevel == 6) {
                for (int i = 0; i < assignedModuleCode.size(); i++) {
                    System.out.format("|    %6s\t\t\t\t\t|\t%4d\t\t\t|\t%3s\t\t    |\n", assignedModuleCode.get(i), studentModuleMarksInList.get(i), studentModuleGradesInList.get(i));
                }
            }

            System.out.print("-----------------------------------------------------------------");

            if(inputLevel != 6){
                int level = inputLevel + 1;
                if(failedModules < 5){
                    System.out.println("\n|  " + "Remarks: You will progress to level " + level+ "\t\t\t\t\t    |");
                }
                else{
                    System.out.println("\n|  " + "Remarks: You will not progress to level " + level + "\t\t\t\t    |");
                }
                System.out.print("-----------------------------------------------------------------");
            }


        }

        assignedModuleCode.clear();
        studentModuleMarksInList.clear();
        studentModuleGradesInList.clear();
    }



    // user interface for students
    public void studentProgramInterface(){
        int inputCourseID, inputStudentID, inputLevel;
        boolean isStudentIDValid;

        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\t1. Register for a Course and its Modules");
        System.out.println("\t\t\t2. View Instructors assigned for course modules");
        System.out.println("\t\t\t3. View assigned modules for level 4");
        System.out.println("\t\t\t4. View assigned modules for level 5");
        System.out.println("\t\t\t5. View registered modules for level 6");
        System.out.println("\t\t\t6. View Results for a module");
        System.out.println("\t\t\t7. Return to main-menu");
        System.out.println("\t\t\t8. Exit");

        System.out.println("\nSelect an option: ");
        int studentOption = sc.nextInt();

        switch(studentOption){
            case 1:
                addNewStudentDetails("Course_Records.txt");
                System.out.println("\nLogged in as Student...");
                studentProgramInterface();

            case 2:
                System.out.println("Enter course ID to view the instructors from: ");
                inputCourseID = sc.nextInt();
                viewInstructorsForSelectedCourse(inputCourseID);
                System.out.println("\n\nLogged in as Student...");
                studentProgramInterface();

            case 3:
                System.out.println("\n----------------View Modules Assigned for Level 4----------------");
                System.out.println("Enter student ID: ");
                inputStudentID = sc.nextInt();
                System.out.println("Enter registered course ID: ");
                inputCourseID = sc.nextInt();
                isStudentIDValid = displayLevel4And5Modules("Level_4_Modules.txt", inputStudentID, inputCourseID, 4);

                if(!isStudentIDValid){
                    System.out.println("\nSorry! either the input student ID or course ID does not match/exist...");
                }

                System.out.println("\n\nLogged in as Student...");
                studentProgramInterface();

            case 4:
                System.out.println("\n----------------View Modules Assigned for Level 5----------------");
                System.out.println("Enter student ID: ");
                inputStudentID = sc.nextInt();
                System.out.println("Enter registered course ID: ");
                inputCourseID = sc.nextInt();
                isStudentIDValid = displayLevel4And5Modules("Level_5_Modules.txt", inputStudentID, inputCourseID, 5);

                if(!isStudentIDValid){
                    System.out.println("\nSorry! either the input student ID or course ID does not match/exist...");
                }

                System.out.println("\n\nLogged in as Student...");
                studentProgramInterface();

            case 5:
                isStudentIDValid = displayStudentDetails("Student_Records.txt", "Student_Module_6_Records.txt");

                if(!isStudentIDValid){
                    System.out.println("\nSorry! the input student ID does not exist...");
                }

                System.out.println("\nLogged in as Student...");
                studentProgramInterface();

            case 6:
                System.out.println("Enter student ID: ");
                inputStudentID = sc.nextInt();
                System.out.println("Enter your current level (4, 5 or 6): ");
                inputLevel = sc.nextInt();
                displayResultSlip(inputStudentID, inputLevel);

                System.out.println("\n\nLogged in as Student...");
                studentProgramInterface();

            case 7:
                System.out.print("\n");
                mainInterface();

            case 8:
                System.exit(0);

            default:
                System.out.println("Sorry! the input option does not exist...\n");

        }
    }




    public static void main(String[] args){
        Student student = new Student();

        System.out.println("-------------------------------------------Welcome to Course Registration System-------------------------------------------");
        student.mainInterface();

    }
}












