# IIT - Monday Group 3 (Admin Management Software Artifact)
Main repository: https://github.com/Ghost-Recon131/IIT-A3

# Admin Software (JavaFx + SpringBoot)
## Running the .jar executable
- Likely requires Java JDK 1.8 from Oracle or any other JDK version that has JavaFx built in
- May need to run via terminal if you have newer version of Java JDK as default or if double-clicking doesn't work
- When launching via double-click, wait upward of 30-45seconds for software to start. It does not give any indication it
  is running unless you start via terminal or watch Task Manager for CPU usage. 

```shell
java -jar admin-software-0.0.1-SNAPSHOT.jar
```
- Internet connection required as this program connects to an AWS RDS instance


## Existing credentials 
You can use the following account to login, or use Register to create a new account.

| Accounts | Username          | Password | Secret Question                      | Secret Question Answer |
|----------|-------------------|----------|--------------------------------------|------------------------|
| 1        | johndoe@gmail.com | password | What is the name of this framework?  | springboot             |


## Program Behaviour
- When viewing logs, and you mark all of them as false positive, 4 new ones show up with the same details.
  This is expected behaviour as the code will populate the notifications tab with dummy data once there are no more entries
  in the database table. 
- When exporting the logs, it will only export in the same directory as the .jar executable


## Known Issues
- Fail to run if your internet connection cannot connect to RDS instance (no offline access)
- Home button icon / notification icon does not function unless you click them a few times. Unknown why this happens as
  no errors or exceptions show up during testing. 


## Manual Compilation 
- It is not possible to compile this program with connection to RDS backend as it uses credentials stored in 
"application-production.properties" file which is not pushed to GitHub to protect the login credentials. The file can be
provided over email / MS Teams.