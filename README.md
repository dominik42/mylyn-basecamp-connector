mylyn-basecamp-connector
========================

Eclipse Mylyn connector for 37signals Basecamp

#Installation via update site
1. Open the Install New Software page: Eclipse / Help / Install New Software ...
2. Click Add...
3. Enter Name: Mylyn Basecamp Connector
4. Enter Location: http://www.todo42.net/mylyn-basecamp-connector/updateSite/site.xml
5. Click Ok to proceed and install

# Usage
Once the plugins are installed and your Eclipse was restartet, you can use the newly installed connector

1. Open the Mylyn Task Repositories View by Eclipse / Window / Show View / Other / Task Repositories
2. Right mouse click and choose "Add Task Repository..."
3. Select "Basecamp Connector" from the list of all available connectors

![Add Task Repository](/dominik42/mylyn-basecamp-connector/blob/master/doc/addTaskRepository.png?raw=true)

4. Enter your account data

![Repository Settings](/dominik42/mylyn-basecamp-connector/blob/master/doc/connectorSettings.png?raw=true)

5. Click "Finish" and wait a second because the connector tries to retrieve all your projects from Basecamp
6. Select your query filter data

![Query Page](/dominik42/mylyn-basecamp-connector/blob/master/doc/queryPage.png?raw=true)