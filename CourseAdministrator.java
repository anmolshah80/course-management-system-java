package CourseManagementSystem;

import java.io.*;
import java.util.*;



public class CourseAdministrator {

    public int courseID;
    public String courseName;
    private String moduleName;
    private String moduleCode;
    private String moduleLeader;
    private int moduleYear;
    private int moduleSemester;
    private String moduleType;

    public String moduleID1;
    public String moduleID2;
    public String moduleID3;
    public String moduleID4;
    public String moduleID5;
    public String moduleID6;
    public String moduleID7;
    public String moduleID8;


    public String[] moduleCodeList = new String[100];

    public int getIndexOf = 0;


    public List<Integer> assignedCourseID = new ArrayList<>();
    public List<String> assignedModuleCode = new ArrayList<>();
    public List<String> moduleCodesInList = new ArrayList<>();
    public List<String> studentRecordsList = new ArrayList<>();
    public List<String> getStudentList = new ArrayList<>();


    public Set<String> moduleCodesInSet = new HashSet<>();
    public Set<Integer> studentIDInSet = new HashSet<>();

    public String instructorsName, instructorsNameLevel6;
    public int courseIDAssigned;



    // attribute courseList created to add the new course data into an ArrayList
    public ArrayList<String> courseList = new ArrayList<>();

    // method to add the course and module details in 'courseList' arraylist
    public void addNewCourseData(int level){
        this.courseList.add(String.valueOf(getCourseID()));
        this.courseList.add(getCourseName());
        this.courseList.add(getModuleCode());
        this.courseList.add(getModuleName());
        this.courseList.add(getModuleLeader());
        this.courseList.add(String.valueOf(getModuleYear()));
        this.courseList.add(String.valueOf(getModuleSemester()));

        if(level == 6){
            this.courseList.add(getModuleType());
        }
    }


    // attribute to add new course records in the arraylist
    public ArrayList<String> addNewCourse = new ArrayList<>();

    // method to add new course details in 'addNewCourse' arraylist
    public void addNewCourse(){
        this.addNewCourse.add(String.valueOf(getCourseID()));
        this.addNewCourse.add(getCourseName());
    }



    // getter and setter methods for the private variables initialised to assign and
    // extract the course and module details
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleLeader() {
        return moduleLeader;
    }

    public void setModuleLeader(String moduleLeader) {
        this.moduleLeader = moduleLeader;
    }

    public int getModuleYear() {
        return moduleYear;
    }

    public void setModuleYear(int moduleYear) {
        this.moduleYear = moduleYear;
    }

    public int getModuleSemester() {
        return moduleSemester;
    }

    public void setModuleSemester(int moduleSemester) {
        this.moduleSemester = moduleSemester;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }




    /*
    * method created to add new course details in the course records file
    * addNewCourseDetails() method takes no parameters but adds the records of new
    * courses in the file. If the course ID or course Name entered already exists in the file
    * then it will not be added again.
    */
    private void addNewCourseRecords(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------------Add New Course Details----------------");
        System.out.println("Enter course ID: ");
        int courseID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter course name: ");
        String courseName = sc.nextLine();

        setCourseID(courseID);
        setCourseName(courseName);

        boolean exceptionsCaught = false;
        boolean courseIDExists = false;
        String[] moduleDetails;
        int courseIDInFile;
        String courseNameInFile;

        try{
            File fp = new File("Course_Records.txt");

            if(fp.createNewFile()){
                System.out.println("\nFile created to add new course records\n");
            }
            else{
                System.out.println("\nFile to store course records already exists\n");
            }

        }
        catch(IOException e){
            System.out.println("\nError creating the file to store course records\n");
        }


        try {
            FileReader fR = new FileReader("Course_Records.txt");
            Scanner scanner = new Scanner(fR);

            while (scanner.hasNextLine()) {
                String courseModule = scanner.nextLine();
                moduleDetails = courseModule.split("\t");

                courseIDInFile = Integer.parseInt(moduleDetails[0]);
                courseNameInFile = moduleDetails[1];

                if(courseID == courseIDInFile || courseName.equals(courseNameInFile)){
                    courseIDExists = true;
                }
            }

            fR.close();

        }
        catch(IOException e){
            System.out.println("\nError reading the file for course records\n");
        }



        try{
            addNewCourse();
            FileWriter courseDatabase = new FileWriter("Course_Records.txt", true);

            Iterator itr = addNewCourse.iterator();
            while(itr.hasNext()){
                String listData = (String)itr.next();

                if(courseIDExists){
                    continue;
                }

                courseDatabase.write(listData + "\t");
            }

            if(courseIDExists){
                System.out.println("Sorry! the input course ID or course name already exists in the file...\n");
            }

            if(!courseIDExists){
                courseDatabase.write("\n");
                System.out.println("Course details added successfully...\n");
            }

            courseDatabase.close();
            addNewCourse.clear();
        }

        catch(IOException e){
            System.out.println("Error writing into the file.\n");
            exceptionsCaught = true;
        }

        // if no exceptions are caught and course ID does not already exist in the file, then user is displayed
        // with the message saying module details added successfully into the file
        if(!exceptionsCaught && !courseIDExists){
            System.out.println("\nNew course details added successfully\n");
        }
    }



    // method to delete course information (course ID and course Name) present in the file "Course_Records.txt"
    private void processCourseDeletionRequests(String courseID){
        boolean isCourseIDValid = false;

        try{
            BufferedReader bR = new BufferedReader(new FileReader("Course_Records.txt"));

            String currentRecord;

            while((currentRecord = bR.readLine()) != null){
                if(currentRecord.contains(courseID)){
                    isCourseIDValid = true;
                }
            }

            bR.close();
        }

        catch(IOException e){
            System.out.println("\nError reading the course records...\n");
        }


        if(isCourseIDValid){
            try {
                File inputFile = new File("Course_Records.txt");
                File tempFile = new File("tempFile.txt");

                BufferedReader bR = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bW = new BufferedWriter(new FileWriter(tempFile));

                String record;

                while ((record = bR.readLine()) != null) {
                    if (record.contains(courseID)) {
                        continue;
                    }

                    bW.write(record);
                    bW.flush();
                    bW.newLine();
                }

                bR.close();
                bW.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);

                System.out.println("Records with course ID <" + courseID + "> has been deleted...\n");
            }
            catch(IOException e){
                System.out.println("Error deleting the course information...\n");
            }

        }

        else{
            System.out.println("Sorry! the input course ID does not exist...\n");
        }
    }


    /*
    * method created to read and delete the modules associated with the course ID and course Name
    * processCourseDeletionRequests() method takes three parameters,
    * fileName: to read and delete the records from,
    * courseID: to delete the records (module details) associated to the course ID,
    * level: to display the message and errors coming from the file (either level 4, 5 or 6)
    */
    private boolean processCourseDeletionRequests(String fileName, String courseID, int level){
        boolean isCourseIDValid = false;  // check if the course ID entered exists in the file
        int countRecords = 0;  // count the number of records with the entered course ID present in the files (level 4, 5 and 6 modules)

        try {
            BufferedReader bR = new BufferedReader(new FileReader(fileName));

            String currentRecord;

            while ((currentRecord = bR.readLine()) != null) {
                if (currentRecord.contains(courseID)) {
                    countRecords += 1;
                    isCourseIDValid = true;
                }
            }
            bR.close();
        }
        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level " + level + " modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level " + level + " modules\n");
        }

        if(isCourseIDValid) {


            try {
                File inputFile = new File(fileName);
                File tempFile = new File("tempFile.txt");

                BufferedReader bR = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bW = new BufferedWriter(new FileWriter(tempFile));

                String record;

                while ((record = bR.readLine()) != null) {
                    if (record.contains(courseID)) {
                        continue;
                    }

                    bW.write(record);
                    bW.flush();
                    bW.newLine();
                }

                bR.close();
                bW.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);

                System.out.println("\nTotal module records present in level " + level + ", associated with the courseID <" + courseID + ">: " + countRecords);
                System.out.println("Modules associated with course ID <" + courseID + "> present in level " + level + " have been deleted successfully...\n");
            }

            catch (FileNotFoundException e) {
                System.out.println("\nFile not found for level " + level + " modules.\n");
            }

            catch (IOException e) {
                System.out.println("\nError deleting the modules information associated to the input course ID in level " + level + "\n");
            }
        }

        return isCourseIDValid;

    }



    // method to execute the course deletion process using the course ID
    private void deleteExistingCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------------Delete Existing Course Records----------------");
        System.out.println("Enter ID of the course to be deleted: ");
        String courseID = sc.nextLine();

        // method to delete the course information based on the input course ID
        processCourseDeletionRequests(courseID);

        // calling the processCourseDeletionRequests() method to read and delete the course details along with the
        // module details associated with that course
        boolean isCourseIDValidInLevel4 = processCourseDeletionRequests("Level_4_Modules.txt", courseID, 4);
        boolean isCourseIDValidInLevel5 = processCourseDeletionRequests("Level_5_Modules.txt", courseID, 5);
        boolean isCourseIDValidInLevel6 = processCourseDeletionRequests("Level_6_Modules.txt", courseID, 6);

        if(!isCourseIDValidInLevel4 && !isCourseIDValidInLevel5 && !isCourseIDValidInLevel6){
            System.out.println("\nSorry! the input course ID does not exist...");
        }
    }




    /*
     * method to read and update the course details along with the module details associated
     * with the course based on course ID and module code
     * processCourseUpdateRequests() method takes four parameters,
     * fileName: to read and update the records in,
     * courseID: to update the records (course details) associated to the course ID,
     * moduleCode: to update the records (module details) associated to the module code,
     * level: to display the message and errors coming from the file (either level 4, 5 or 6)
     */
    // method to read and update the course records based on course ID and module code
    private boolean processCourseUpdateRequests(String fileName, String courseID, String moduleCode, int level) {
        boolean isCourseIDValid = false;  // check if the course ID entered exists in the file
        boolean isModuleCodeValid = false;  // check if the module code entered exists in the file

        try {
            BufferedReader bR = new BufferedReader(new FileReader(fileName));

            String currentRecord;

            while ((currentRecord = bR.readLine()) != null) {
                if (currentRecord.contains(courseID) && currentRecord.contains(moduleCode)) {
                    isCourseIDValid = true;
                    isModuleCodeValid = true;
                }
            }

            bR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found for level " + level + " modules.\n");
        } catch (IOException e) {
            System.out.println("\nError reading the file contents of level " + level + " modules\n");
        }

        if (isCourseIDValid && isModuleCodeValid) {
            String courseIDRecord;
            String courseNameRecord;
            String moduleNameRecord;
            String moduleCodeRecord;
            String moduleLeaderRecord;
            int moduleYearRecord;
            int moduleSemesterRecord;
            String moduleTypeRecord = "optional";




            String record, record2;


            try {
                File inputFile = new File(fileName);

                BufferedReader bR = new BufferedReader(new FileReader(inputFile));


                while ((record = bR.readLine()) != null) {

                    StringTokenizer st = new StringTokenizer(record, "\t");
                    if (record.contains(courseID) && record.contains(moduleCode)) {
                        System.out.println("\n----------------" + st.nextToken() + " " + st.nextToken() + " Level " + level + " Modules----------------");
                        System.out.println("Module Code: " + st.nextToken());
                        System.out.println("Module Name: " + st.nextToken());
                        System.out.println("Module Leader: " + st.nextToken());
                        System.out.println("Year: " + st.nextToken());
                        System.out.println("Semester: " + st.nextToken());

                        if(level == 6){
                            System.out.println("Module Type: " + st.nextToken());
                        }
                    }

                }
                bR.close();
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found for level " + level + " modules.\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of level " + level + " modules\n");
            }

            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n----------------Update details of course ID <" + courseID + "> module code <" + moduleCode +">----------------");
                System.out.println("Enter updated course ID: ");
                courseIDRecord = sc.nextLine();
                System.out.println("Enter updated course name: ");
                courseNameRecord = sc.nextLine();
                System.out.println("Enter updated module code: ");
                moduleCodeRecord = sc.nextLine();
                System.out.println("Enter updated module name: ");
                moduleNameRecord = sc.nextLine();
                System.out.println("Enter updated module leader name: ");
                moduleLeaderRecord = sc.nextLine();

                if(level == 6){
                    System.out.println("Enter updated module type (mandatory or optional): ");
                    moduleTypeRecord = sc.nextLine();
                }

                System.out.println("Enter updated module year: ");
                moduleYearRecord = sc.nextInt();
                System.out.println("Enter updated module semester: ");
                moduleSemesterRecord = sc.nextInt();



                File inputFile = new File(fileName);
                File tempFile = new File("tempFile.txt");

                BufferedWriter bW = new BufferedWriter(new FileWriter(tempFile));
                BufferedReader bR2 = new BufferedReader(new FileReader(fileName));

                while ((record2 = bR2.readLine()) != null) {
                    if (record2.contains(courseID) && record2.contains(moduleCode)) {
                        bW.write(courseIDRecord + "\t" + courseNameRecord + "\t" + moduleCodeRecord + "\t" + moduleNameRecord + "\t" + moduleLeaderRecord + "\t" + moduleYearRecord + "\t" + moduleSemesterRecord);

                    if(level == 6){
                        bW.write("\t" + moduleTypeRecord);
                    }
                }

                    else {
                        bW.write(record2);
                    }

                    bW.flush();
                    bW.newLine();
                }

                bW.close();
                bR2.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);

                System.out.println("\nRecords with course ID - " + courseID + " and module code - " + moduleCode + " have been updated in level " + level + " modules file.\n");
            }

            catch (FileNotFoundException e) {
                System.out.println("\nFile not found for level " + level + " modules.\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of level " + level + " modules\n");
            }
        }


        return isCourseIDValid;
    }





    // method to update the course and module details
    private void updateExistingCourse(){
        boolean isFoundInLevel4, isFoundInLevel5, isFoundInLevel6;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------------Update Existing Course Records----------------");
        System.out.println("Enter course ID to be updated: ");
        String courseID = sc.nextLine();
        System.out.println("Enter module code to be updated: ");
        String moduleCode = sc.nextLine();

        isFoundInLevel4 = processCourseUpdateRequests("Level_4_Modules.txt", courseID, moduleCode, 4);
        isFoundInLevel5 = processCourseUpdateRequests("Level_5_Modules.txt", courseID, moduleCode, 5);
        isFoundInLevel6 = processCourseUpdateRequests("Level_6_Modules.txt", courseID, moduleCode, 6);

        if(!isFoundInLevel4 && !isFoundInLevel5 && !isFoundInLevel6){
            System.out.println("\nSorry! The course ID or module code entered does not exist in the file...\n");
        }
    }




    // method created to add level 4 module details in the file
    private void addLevel4Modules(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------------Add Level 4 Modules----------------");
        System.out.println("Enter course ID: ");
        int courseID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter course Name: ");
        String courseName = sc.nextLine();
        System.out.println("Enter module code: ");
        String moduleCode = sc.nextLine();
        System.out.println("Enter module name: ");
        String moduleName = sc.nextLine();
        System.out.println("Enter module leader name: ");
        String moduleLeader = sc.nextLine();
        System.out.println("Enter module year: ");
        int moduleYear = sc.nextInt();
        System.out.println("Enter module semester: ");
        int moduleSemester = sc.nextInt();


        setCourseID(courseID);
        setCourseName(courseName);
        setModuleCode(moduleCode);
        setModuleName(moduleName);
        setModuleLeader(moduleLeader);
        setModuleYear(moduleYear);
        setModuleSemester(moduleSemester);


        boolean exceptionsCaught = false;
        try{
            File fp1 = new File("Level_4_Modules.txt");

            if(fp1.createNewFile()){
                System.out.println("\nFile created for level 4 modules...\n");
            }
            else{
                System.out.println("\nLevel 4 modules file already exists...\n");
            }

        }
        catch(IOException e){
            System.out.println("\nError creating the file\n");
        }

        try{
            addNewCourseData(4);
            FileWriter level4ModuleDatabase = new FileWriter("Level_4_Modules.txt", true);

            Iterator itr = courseList.iterator();
            while(itr.hasNext()){
                String listData = (String)itr.next();
                level4ModuleDatabase.write(listData + "\t");
            }
            level4ModuleDatabase.write("\n");
            level4ModuleDatabase.close();

            courseList.clear();
        }
        catch(IOException e){
            System.out.println("\nError writing into the file\n");
            exceptionsCaught = true;
        }

        // if no exceptions are caught then user is displayed with the message saying module details
        // added successfully into the file
        if(!exceptionsCaught){
            System.out.println("\nLevel 4 module details added successfully...\n");
        }

    }


    // method created to add level 5 module details in the file
    private void addLevel5Modules(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------------Add Level 5 Modules----------------");
        System.out.println("Enter course ID: ");
        int courseID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter course Name: ");
        String courseName = sc.nextLine();
        System.out.println("Enter module code: ");
        String moduleCode = sc.nextLine();
        System.out.println("Enter module name: ");
        String moduleName = sc.nextLine();
        System.out.println("Enter module leader name: ");
        String moduleLeader = sc.nextLine();
        System.out.println("Enter module year: ");
        int moduleYear = sc.nextInt();
        System.out.println("Enter module semester: ");
        int moduleSemester = sc.nextInt();

        setCourseID(courseID);
        setCourseName(courseName);
        setModuleCode(moduleCode);
        setModuleName(moduleName);
        setModuleLeader(moduleLeader);
        setModuleYear(moduleYear);
        setModuleSemester(moduleSemester);


        boolean exceptionsCaught = false;
        try{
            File fp2 = new File("Level_5_Modules.txt");

            if(fp2.createNewFile()){
                System.out.println("\nFile created for level 5 modules...\n");
            }
            else{
                System.out.println("\nLevel 5 modules file already exists...\n");
            }

        }
        catch(IOException e){
            exceptionsCaught = true;
            System.out.println("\nError creating the file.\n");
        }

        try{
            addNewCourseData(5);
            FileWriter level5ModuleDatabase = new FileWriter("Level_5_Modules.txt", true);

            Iterator itr = courseList.iterator();
            while(itr.hasNext()){
                String listData = (String)itr.next();
                level5ModuleDatabase.write(listData + "\t");
            }
            level5ModuleDatabase.write("\n");

            level5ModuleDatabase.close();
            courseList.clear();
        }
        catch(IOException e){
            System.out.println("\nError writing into the file.\n");
            exceptionsCaught = true;
        }

        if(!exceptionsCaught){
            System.out.println("\nLevel 5 module details added successfully\n");
        }
    }



    // method created to add level 6 module details in the file
    private void addLevel6Modules(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------------Add Level 6 Modules----------------");
        System.out.println("Enter course ID: ");
        int courseID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter course Name: ");
        String courseName = sc.nextLine();
        System.out.println("Enter module code: ");
        String moduleCode = sc.nextLine();
        System.out.println("Enter module name: ");
        String moduleName = sc.nextLine();
        System.out.println("Enter module leader name: ");
        String moduleLeader = sc.nextLine();
        System.out.println("Enter module year: ");
        int moduleYear = sc.nextInt();
        System.out.println("Enter module semester: ");
        int moduleSemester = sc.nextInt();
        System.out.println("Enter module type (mandatory or optional): ");
        String moduleType = sc.next();

        setCourseID(courseID);
        setCourseName(courseName);
        setModuleCode(moduleCode);
        setModuleName(moduleName);
        setModuleLeader(moduleLeader);
        setModuleYear(moduleYear);
        setModuleSemester(moduleSemester);
        setModuleType(moduleType);


        boolean exceptionsCaught = false;
        try{
            File fp = new File("Level_6_Modules.txt");

            if(fp.createNewFile()){
                System.out.println("\nFile created for level 6 modules...\n");
            }
            else{
                System.out.println("\nLevel 6 modules file already exists...\n");
            }

        }
        catch(IOException e){
            exceptionsCaught = true;
            System.out.println("\nError creating the file.\n");
        }


        try{
            addNewCourseData(6);
            FileWriter level6ModuleDatabase = new FileWriter("Level_6_Modules.txt", true);

            Iterator itr = courseList.iterator();
            while(itr.hasNext()){
                String listData = (String)itr.next();
                level6ModuleDatabase.write(listData + "\t");
            }
            level6ModuleDatabase.write("\n");
            level6ModuleDatabase.close();

            courseList.clear();
        }
        catch(IOException e){
            System.out.println("\nError creating the file.\n");
            exceptionsCaught = true;
        }

        if(!exceptionsCaught){
            System.out.println("\nLevel 6 module details added successfully.\n");
        }
    }


    // method created to read and display level 4 module details
    private void level4ModulesDisplay(){
        String[] moduleDetails;
        int courseID;
        String courseName;
        String moduleCode;
        String moduleName;
        String moduleLeader;
        int moduleYear;
        int moduleSemester;
        int countRecords = 0;
        int index = 1;

        try{
            FileReader level4ModuleDetails = new FileReader("Level_4_Modules.txt");
            Scanner sc = new Scanner(level4ModuleDetails);

            System.out.println("\n--------------------------------Level 4 Modules--------------------------------");


            while(sc.hasNextLine()){
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");

                courseID = Integer.parseInt(moduleDetails[0]);
                courseName = moduleDetails[1];
                moduleCode = moduleDetails[2];
                moduleName = moduleDetails[3];
                moduleLeader = moduleDetails[4];
                moduleYear = Integer.parseInt(moduleDetails[5]);
                moduleSemester = Integer.parseInt(moduleDetails[6]);



                System.out.println(index + ". " + "Course ID: " + courseID);
                System.out.println("\tCourse Name: " + courseName);
                System.out.println("\tModule Code: " + moduleCode);
                System.out.println("\tModule Name: " + moduleName);
                System.out.println("\tModule Leader: " + moduleLeader);
                System.out.println("\tYear: " + moduleYear);
                System.out.println("\tSemester: " + moduleSemester);
                System.out.print("\n");

                countRecords += 1;
                index++;
            }

            level4ModuleDetails.close();

            System.out.println("\nTotal modules present for level 4: " + countRecords);

        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 4 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 4 modules\n");
        }

    }


    // method created to read and display level 5 module details
    private void level5ModulesDisplay(){
        String[] moduleDetails;
        int courseID;
        String courseName;
        String moduleCode;
        String moduleName;
        String moduleLeader;
        int moduleYear;
        int moduleSemester;
        int countRecords = 0;
        int index = 1;

        try{
            FileReader level5ModuleDetails = new FileReader("Level_5_Modules.txt");
            Scanner sc = new Scanner(level5ModuleDetails);

            System.out.println("\n--------------------------------Level 5 Modules--------------------------------");

            while(sc.hasNextLine()){
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");

                courseID = Integer.parseInt(moduleDetails[0]);
                courseName = moduleDetails[1];
                moduleCode = moduleDetails[2];
                moduleName = moduleDetails[3];
                moduleLeader = moduleDetails[4];
                moduleYear = Integer.parseInt(moduleDetails[5]);
                moduleSemester = Integer.parseInt(moduleDetails[6]);


                System.out.println(index + ". " + "Course ID: " + courseID);
                System.out.println("\tCourse Name: " + courseName);
                System.out.println("\tModule Code: " + moduleCode);
                System.out.println("\tModule Name: " + moduleName);
                System.out.println("\tModule Leader: " + moduleLeader);
                System.out.println("\tYear: " + moduleYear);
                System.out.println("\tSemester: " + moduleSemester);
                System.out.print("\n");

                countRecords += 1;
                index++;
            }

            level5ModuleDetails.close();

            System.out.println("\nTotal modules present for level 5: " + countRecords);

        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 5 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 5 modules\n");
        }
    }


    // method to read and display level 6 module details
    private void level6ModulesDisplay(){
        String[] moduleDetails;
        int courseID;
        String courseName;
        String moduleCode;
        String moduleName;
        String moduleLeader;
        int moduleYear;
        int moduleSemester;
        String moduleType;
        int countRecords = 0;
        int index = 1;

        try{
            FileReader level6ModuleDetails = new FileReader("Level_6_Modules.txt");
            Scanner sc = new Scanner(level6ModuleDetails);

            System.out.println("\n--------------------------------Level 6 Modules--------------------------------");

            while(sc.hasNextLine()){
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

                System.out.println(index + ". " + "Course ID: " + courseID);
                System.out.println("\tCourse Name: " + courseName);
                System.out.println("\tModule Code: " + moduleCode);
                System.out.println("\tModule Name: " + moduleName);
                System.out.println("\tModule Leader: " + moduleLeader);
                System.out.println("\tYear: " + moduleYear);
                System.out.println("\tSemester: " + moduleSemester);
                System.out.println("\tModule Type: " + moduleType);
                System.out.print("\n");

                countRecords += 1;
                index++;
            }

            level6ModuleDetails.close();

            System.out.println("\nTotal modules present for level 6: " + countRecords);

        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for level 6 modules.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of level 6 modules\n");
        }
    }





    // method to add modules of level 4 and level 5 as default modules for a course
    public void assignLevel4And5Modules(int studentID, int courseID, int level) {
        String fileName = "Level_4_Modules.txt";

        String[] moduleDetails;



        if (level == 5) {
            fileName = "Level_5_Modules.txt";
        }

        try {

            System.out.println("\n----------------Assigned modules for level " + level + "----------------");
            FileReader fR = new FileReader(fileName);
            Scanner sc = new Scanner(fR);

            while (sc.hasNextLine()) {
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");


                if(moduleDetails[0].equals(String.valueOf(courseID))){
                    moduleCodesInList.add(moduleDetails[2] + "\t");
                }

            }

            fR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found to read level " + level + " modules");
        } catch (IOException e) {
            System.out.println("\nCannot read level " + level + " file contents");
        }


        String[] studentDetails;


        // read students personal records
        try {
            FileReader fR = new FileReader("Student_Records.txt");
            Scanner sc = new Scanner(fR);

            while (sc.hasNextLine()) {
                String studentInfo = sc.nextLine();
                studentDetails = studentInfo.split("\t");


                if(studentDetails[0].equals(String.valueOf(studentID))) {
                    studentRecordsList.add(studentDetails[0] + "\t" + studentDetails[3]);
                }


            }

            fR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found for student records file");
        } catch (IOException e) {
            System.out.println("\nError reading student records file");
        }


        // displaying assigned level 4 and level 5 modules to the registered students based on their selected course
        if (level == 4 || level == 5) {

            StringBuilder sb = new StringBuilder();


            for(String s1: studentRecordsList){
                sb.append(s1);
                sb.append("\t");

                for(String s: moduleCodesInList){
                    sb.append(s);
                    sb.append("\t");
                }

                sb.append("\n");
            }

            System.out.println(sb.toString());

        }

        moduleCodesInList.clear();
        studentRecordsList.clear();
    }



    // method to display the registered students assigned to the instructor (of level 4 and level 5)
    public int assignLevel4And5Modules(String instructorsName, int courseID, int level) {
        String fileName = "Level_4_Modules.txt";


        String[] moduleDetails;

        List<String> courseIDInFile = new ArrayList<>();



        if (level == 5) {
            fileName = "Level_5_Modules.txt";
        }

        try {
            FileReader fR = new FileReader(fileName);
            Scanner sc = new Scanner(fR);

            while (sc.hasNextLine()) {
                String courseModule = sc.nextLine();
                moduleDetails = courseModule.split("\t");

                if(moduleDetails[4].equals(instructorsName)){
                    courseIDInFile.add(moduleDetails[0]);
                }



                if(moduleDetails[0].equals(String.valueOf(courseID))){
                    moduleCodesInList.add(moduleDetails[2] + "\t");
                }


            }

            fR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found to read level " + level + " modules");
        } catch (IOException e) {
            System.out.println("\nCannot read level " + level + " file contents");
        }


        String[] studentDetails;


        // read students personal records
        try {
            FileReader fR = new FileReader("Student_Records.txt");
            Scanner sc = new Scanner(fR);

            while (sc.hasNextLine()) {
                String studentInfo = sc.nextLine();
                studentDetails = studentInfo.split("\t");



                if(studentDetails[3].equals(String.valueOf(courseID)) && courseIDInFile.contains(String.valueOf(courseID))) {
                    studentRecordsList.add(studentDetails[0]);
                    getStudentList.add(studentDetails[0]);
                }

            }

            fR.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found for student records file");
        } catch (IOException e) {
            System.out.println("\nError reading student records file");
        }


        // displaying assigned level 4 and level 5 modules to the registered students based on their selected course
        if (level == 4 || level == 5) {

            StringBuilder sb = new StringBuilder();

            System.out.println("For module code: " + assignedModuleCode.get(getIndexOf));
            getIndexOf++;
            System.out.println("Students Assigned: ");

            for (String s1 : getStudentList) {
                sb.append(s1);
                sb.append("\n");
            }

            System.out.println(sb.toString());



        }

        return getStudentList.size();

    }




    // method created to assign level 4 or 5 modules to the students registered to a particular course
    public boolean displayLevel4And5Modules(String fileName, int inputStudentID, int courseID, int level){
        boolean isStudentIDValid = false;

        int studentIDInFile;
        String[] studentRecords;
        String[] moduleRecords;


        // reading the student personal records from the file
        try{
            FileReader fR = new FileReader("Student_Records.txt");
            Scanner sc = new Scanner(fR);


            while(sc.hasNextLine()){
                String studentDetails = sc.nextLine();

                studentRecords = studentDetails.split("\t");

                studentIDInFile = Integer.parseInt(studentRecords[0]);

                if(inputStudentID == studentIDInFile && courseID == Integer.parseInt(studentRecords[3])){
                    System.out.println("\n----------------Student Details----------------");
                    System.out.println("Student ID: " + studentIDInFile);
                    System.out.println("Student Name: " + studentRecords[1]);
                    System.out.println("Email Address: " + studentRecords[2]);
                    System.out.println("Course ID: " + studentRecords[3]);
                    System.out.println("Course Name: " + studentRecords[4]);
                    isStudentIDValid = true;
                }
            }

            fR.close();
        }

        catch(FileNotFoundException e){
            System.out.println("\nFile not found for student records.\n");
        }

        catch(IOException e){
            System.out.println("\nError reading the file contents of student records\n");
        }


        if(isStudentIDValid) {
            System.out.println("Assigned module codes: ");


            // reading the modules for level 4 or 5 present in the file
            try {
                FileReader fR = new FileReader(fileName);
                Scanner sc = new Scanner(fR);

                while (sc.hasNextLine()) {
                    String studentDetails = sc.nextLine();


                    moduleRecords = studentDetails.split("\t");


                    if (courseID == Integer.parseInt(moduleRecords[0])) {
                        System.out.print("<" + moduleRecords[2] + ">" + "\t");
                    }

                }

                fR.close();
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found for level " + level + " module records.\n");
            } catch (IOException e) {
                System.out.println("\nError reading the file contents of level " + level + " module records\n");
            }

        }

        return isStudentIDValid;

    }


    // method to remove instructors from modules assigned
    private boolean removeInstructorFromModules(String fileName, String instructorName) {

        boolean isInstructorValid = false;

        String[] instructorModuleRecords;


        try{
            FileReader fR = new FileReader(fileName);

            Scanner fileScan = new Scanner(fR);

            while(fileScan.hasNextLine()){
                String instructorDetails = fileScan.nextLine();

                instructorModuleRecords = instructorDetails.split("\t");

                if(instructorModuleRecords[4].equals(instructorName)){
                    isInstructorValid = true;
                }
            }
            fR.close();
        }
        catch(IOException e){
            System.out.println("\nError reading the contents of modules file");
        }


        if(isInstructorValid){
            try {
                File inputFile = new File(fileName);
                File tempFile = new File("tempFile.txt");

                BufferedReader bR = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bW = new BufferedWriter(new FileWriter(tempFile));

                String record;

                while ((record = bR.readLine()) != null) {
                    if (record.contains(instructorName)) {
                        continue;
                    }

                    bW.write(record);
                    bW.flush();
                    bW.newLine();
                }

                bR.close();
                bW.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);

                System.out.println("\nRecords with instructor name <" + instructorName + "> have been deleted successfully...\n");
            }
            catch(IOException e){
                System.out.println("\nError deleting the instructor details from the file...\n");
            }

        }

        return isInstructorValid;
    }





    // main user interface for the course registration system
    public void mainInterface(){
        Student student = new Student();
        Instructor instructor = new Instructor();

        Scanner sc = new Scanner(System.in);
        System.out.println("Login as: ");
        System.out.println("\t\t\t1. Student\n\t\t\t2. Course Administrator\n\t\t\t3. Instructor");
        System.out.println("\nSelect an option: ");
        int option = sc.nextInt();

        if(option <= 0 || option > 3){
            System.out.println("Sorry! the selected option does not exist\n");
        }

        switch(option){
            case 1:
                System.out.println("\nLogged in as student!!!\n");
                student.studentProgramInterface();


            case 2:
                System.out.println("\nLogged in as Course Administrator!!!\n");
                adminProgramInterface();


            case 3:
                System.out.println("\nLogged in as Instructor!!!\n");
                instructor.instructorProgramInterface();
        }
    }



    // user interface for course administrator
    public void adminProgramInterface(){
        Student student = new Student();

        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\t1. Add a new course");
        System.out.println("\t\t\t2. Add level 4 modules for a course");
        System.out.println("\t\t\t3. Add level 5 modules for a course");
        System.out.println("\t\t\t4. Add level 6 modules for a course");
        System.out.println("\t\t\t5. Update course & its module records");
        System.out.println("\t\t\t6. Delete course & its module records");
        System.out.println("\t\t\t7. Remove Instructor from assigned modules");
        System.out.println("\t\t\t8. View level 4 modules from a course");
        System.out.println("\t\t\t9. View level 5 modules from a course");
        System.out.println("\t\t\t10. View level 6 modules from a course");
        System.out.println("\t\t\t11. View results slip of a student");
        System.out.println("\t\t\t12. Return to main-menu");
        System.out.println("\t\t\t13. Exit");

        System.out.println("\nSelect an option: ");
        int adminOption = sc.nextInt();
        sc.nextLine();

        switch(adminOption){
            case 1:
                addNewCourseRecords();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 2:
                addLevel4Modules();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 3:
                addLevel5Modules();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 4:
                addLevel6Modules();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 5:
                updateExistingCourse();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 6:
                deleteExistingCourse();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 7:
                System.out.println("\n---------------Remove Instructor from the modules assigned---------------");

                System.out.println("Enter instructor name: ");
                String instructorName = sc.nextLine();

                boolean isInstructorValidInLevel4 = removeInstructorFromModules("Level_4_Modules.txt", instructorName);
                boolean isInstructorValidInLevel5 = removeInstructorFromModules("Level_5_Modules.txt", instructorName);
                boolean isInstructorValidInLevel6 = removeInstructorFromModules("Level_6_Modules.txt", instructorName);

                if(!isInstructorValidInLevel4 && !isInstructorValidInLevel5 && !isInstructorValidInLevel6){
                    System.out.println("\nSorry! the input instructor name does not exist...");
                }

                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 8:
                level4ModulesDisplay();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 9:
                level5ModulesDisplay();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 10:
                level6ModulesDisplay();
                System.out.println("\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 11:
                System.out.println("Enter student ID: ");
                int inputStudentID = sc.nextInt();
                System.out.println("Enter your level (4, 5 or 6): ");
                int inputLevel = sc.nextInt();
                student.displayResultSlip(inputStudentID, inputLevel);

                System.out.println("\n\nLogged in as Course Administrator...\n");
                adminProgramInterface();

            case 12:
                System.out.print("\n");
                mainInterface();

            case 13:
                System.exit(0);

            default:
                System.out.println("Sorry! the input option does not exist");
        }
    }




    public static void main(String[] args) {
        CourseAdministrator cA = new CourseAdministrator();

        System.out.println("-------------------------------------------Welcome to Course Registration System-------------------------------------------");
        cA.mainInterface();

    }
}
