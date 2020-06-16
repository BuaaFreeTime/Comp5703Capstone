# Comp5703Capstone
## Capstone project management system---SpringBoot+Maven+Firebase
In current stage, our university staffs and students are still sending mail manually to complete the grouping, project choosing, and projects/students managing. So in this case our project is designed for real capstone project management scenario, which will be used by clients, students, and administrator. The online automated capstone management system can greatly reduce the labour costs and improve work efficiency.

## To run the code in idea (JDK 11)
1. Make sure the Comp5703Capstone master file is complete.(firebase key in "src/main/resources/firebase/")
2. Import project from Comp5703Capstone file
3. Import dependencies via Maven(3.6)
4. Edit configuration for springboot project

## Deployment
1. Move the war package(ROOT.war in Comp5703Capstone master fil) to the webapps in the tomcat(9.0.33) directory of the server without decompression and without any operation. If there is a ROOT folder in the webapps directory, just delete it.
2. Run tomcat and use url "localhost:8080/" to get login page
