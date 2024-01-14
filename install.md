
## Step 1. Install Java 11 (OpenJDK)
ThingsBoard service is running on Java 11. Follow this instructions to install OpenJDK 11:
```text
sudo apt update
```
```text
sudo apt install openjdk-11-jdk
```
Please don’t forget to configure your operating system to use OpenJDK 11 by default. You can configure which version is the default using the following command:

```text
sudo update-alternatives --config java
```
You can check the installation using the following command:
```text
java -version
```
Expected command output is:
```
openjdk version "11.0.xx"
OpenJDK Runtime Environment (...)
OpenJDK 64-Bit Server VM (build ...)
```

## Step 2. Build Source with Maven
ThingsBoard build requires Maven 3.1.0+.
```text
sudo apt-get install maven
```
You can clone source code of the project from the official github repo.
```
git clone https://github.com/d42y/thingsboard
```
```text
cd thingsboard
```
### Build
Run the following command from the thingsboard folder to build the project:
```text
mvn clean install -DskipTests
```
## Step 3. ThingsBoard service installation
Copy installation package.
```text
sudo cp ~/application/target/thingsboard.deb
```
Install ThingsBoard as a service
```text
sudo dpkg -i thingsboard.deb
```
## Step 4. Configure ThingsBoard database
ThingsBoard is able to use SQL or hybrid database approach. See corresponding architecture page for more details.
### PostgreSQL Installation
Instructions listed below will help you to install PostgreSQL.
install **wget** if not already installed:
```text
sudo apt install -y wget
```
import the repository signing key:
```text
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
```
add repository contents to your system:
```text
echo "deb https://apt.postgresql.org/pub/repos/apt/ $(lsb_release -cs)-pgdg main" | sudo tee  /etc/apt/sources.list.d/pgdg.list
```
install and launch the postgresql service:
```text
sudo apt -y install postgresql-15
```
```text
sudo service postgresql start
```

Once PostgreSQL is installed you may want to create a new user or set the password for the the main user. The instructions below will help to set the password for main postgresql user

```text
sudo su - postgres
```
```text
psql
```
```text
\password
```
```text
\q
```

Then, press “Ctrl+D” to return to main user console and connect to the database to create thingsboard DB:
```text
psql -U postgres -d postgres -h 127.0.0.1 -W
```
```text
CREATE DATABASE thingsboard;
```
```text
\q
```
### Cassandra Installation
Instructions listed below will help you to install Cassandra.
Add cassandra repository
```text
echo "deb https://debian.cassandra.apache.org 41x main" | sudo tee /etc/apt/sources.list.d/cassandra.sources.list
```
```text
curl https://downloads.apache.org/cassandra/KEYS | sudo apt-key add -
```
Cassandra installation
```text
sudo apt-get install cassandra
```
Tools installation
```text
sudo apt-get install cassandra-tools
```
### ThingsBoard Configuration
Edit ThingsBoard configuration file
```text
sudo nano /etc/thingsboard/conf/thingsboard.conf
```
Add the following lines to the configuration file. Don’t forget to replace “PUT_YOUR_POSTGRESQL_PASSWORD_HERE” with your real postgres user password:
```text
# DB Configuration 
export DATABASE_TS_TYPE=cassandra
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/thingsboard
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=PUT_YOUR_POSTGRESQL_PASSWORD_HERE
```
You can optionally add the following parameters to reconfigure your ThingsBoard instance to connect to external Cassandra nodes:
```text
export CASSANDRA_CLUSTER_NAME=Thingsboard Cluster
export CASSANDRA_KEYSPACE_NAME=thingsboard
export CASSANDRA_URL=127.0.0.1:9042
export CASSANDRA_USE_CREDENTIALS=false
export CASSANDRA_USERNAME=
export CASSANDRA_PASSWORD=
```
## Step 5. Choose ThingsBoard queue service
ThingsBoard is able to use various messaging systems/brokers for storing the messages and communication between ThingsBoard services. How to choose the right queue implementation?

### In Memory
In Memory queue implementation is built-in and default. It is useful for development(PoC) environments and is not suitable for production deployments or any sort of cluster deployments.
```
No action required. Work by default.
```

### Kafka
Kafka is recommended for production deployments. This queue is used on the most of ThingsBoard production environments now. It is useful for both on-prem and private cloud deployments. It is also useful if you like to stay independent from your cloud provider. However, some providers also have managed services for Kafka. See AWS MSK for example.

Kafka uses ZooKeeper so you need to first install ZooKeeper server:
```text
sudo apt-get install zookeeper
```
#### Setup ZooKeeper Systemd Unit file
Create systemd unit file for Zookeeper:
```text
sudo nano /etc/systemd/system/zookeeper.service
```
Add below content:
```text
[Unit]
Description=Apache Zookeeper server
Documentation=http://zookeeper.apache.org
Requires=network.target remote-fs.target
After=network.target remote-fs.target

[Service]
Type=simple
ExecStart=/usr/local/kafka/bin/zookeeper-server-start.sh /usr/local/kafka/config/zookeeper.properties
ExecStop=/usr/local/kafka/bin/zookeeper-server-stop.sh
Restart=on-abnormal

[Install]
WantedBy=multi-user.target
```
Enable and start ZooKeeper:
```text
sudo systemctl enable --now zookeeper
```
#### Install Kafka
```text
wget https://downloads.apache.org/kafka/3.6.1/kafka_2.13-3.6.1.tgz
```
```text
tar xzf kafka_2.13-3.6.1.tgz
```
```text
sudo mv kafka_2.13-3.6.1.tgz /usr/local/kafka
```
#### Setup Kafka Systemd Unit file
Create systemd unit file for Kafka:
```text
sudo nano /etc/systemd/system/kafka.service
```
Add the below content. Make sure to replace “/usr/lib/jvm/java-11-openjdk-xxx” with your real JAVA_HOME path as per the Java installed on your system, by default like “/usr/lib/jvm/java-11-openjdk-xxx”:
```text
[Unit]
Description=Apache Kafka Server
Documentation=http://kafka.apache.org/documentation.html
Requires=zookeeper.service

[Service]
Type=simple
Environment="JAVA_HOME=/usr/lib/jvm/java-11-openjdk-xxx"
ExecStart=/usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server.properties
ExecStop=/usr/local/kafka/bin/kafka-server-stop.sh

[Install]
WantedBy=multi-user.target
```
Enable and start Kafka:
```text
sudo systemctl enable --now kafka
```
### ThingsBoard Configuration
Edit ThingsBoard configuration file
```text
sudo nano /etc/thingsboard/conf/thingsboard.conf
```
Add the following line to the configuration file. Don’t forget to replace “localhost:9092” with your real Kafka bootstrap servers:
```text
export TB_QUEUE_TYPE=kafka
export TB_KAFKA_SERVERS=localhost:9092
```
## Step 6. [Optional] Memory update for slow machines (1GB of RAM)
Edit ThingsBoard configuration file
```text
sudo nano /etc/thingsboard/conf/thingsboard.conf
```
Add the following lines to the configuration file.
```text
# Update ThingsBoard memory usage and restrict it to 256MB in /etc/thingsboard/conf/thingsboard.conf
export JAVA_OPTS="$JAVA_OPTS -Xms256M -Xmx256M"
```
## Step 7. Run installation script
Once ThingsBoard service is installed and DB configuration is updated, you can execute the following script:
```text
# --loadDemo option will load demo data: users, devices, assets, rules, widgets.
sudo /usr/share/thingsboard/bin/install/install.sh --loadDemo
```
## Step 8. Start ThingsBoard service
Execute the following command to start ThingsBoard:
```text
sudo service thingsboard start
```
Once started, you will be able to open Web UI using the following link:
```text
http://localhost:8080/
```
The following default credentials are available if you have specified –loadDemo during execution of the installation script:

System Administrator: sysadmin@thingsboard.org / sysadmin
Tenant Administrator: tenant@thingsboard.org / tenant
Customer User: customer@thingsboard.org / customer
You can always change passwords for each account in account profile page.

## Troubleshooting
ThingsBoard logs are stored in the following directory:
```text
/var/log/thingsboard
```
You can issue the following command in order to check if there are any errors on the backend side:
```text
cat /var/log/thingsboard/thingsboard.log | grep ERROR
```
