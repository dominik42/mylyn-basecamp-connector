mylyn-basecamp-connector
========================

Eclipse Mylyn connector for 37signals Basecamp

#Installation via update site
1. Open the Install New Software page: Eclipse / Help / Install New Software ...
2. Click Add...
3. Enter Name: Mylyn Basecamp Connector
4. Enter Location: http://www.todo42.net/mylyn-basecamp-connector/updateSite
5. Click Ok to proceed and install

# Usage
Once the plugins are installed and your Eclipse was restartet, you can use the newly installed connector

* Open the Mylyn Task Repositories View by Eclipse / Window / Show View / Other / Task Repositories
* Right mouse click and choose "Add Task Repository..."
* Select "Basecamp Connector" from the list of all available connectors

![Add Task Repository](/dominik42/mylyn-basecamp-connector/blob/master/doc/addTaskRepository.png?raw=true)

* Enter your account data
  * Server : your Basecamp project URL as &lt;your-project&gt;.basecamphq.com (e.g. efinia.basecamphq.com) ; 
           you can find and change this URL at your Basecamp account settings (login at Basecamp and see under  My Info / Account)
  * Label : This description is used by Mylyn as name for this repository in the Task Repositories
  * User ID : your Basecamp account user
  * Password : your Basecamp account password

![Repository Settings](/dominik42/mylyn-basecamp-connector/blob/master/doc/connectorSettings.png?raw=true)

* Click "Finish" and wait a second because the connector tries to retrieve some initial properties from Basecamp
* Select your query filter data

![Query Page](/dominik42/mylyn-basecamp-connector/blob/master/doc/queryPage.png?raw=true)