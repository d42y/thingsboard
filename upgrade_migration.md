# Upgrade and Migration

On the first computer, backup the database using postgres dump tool:
```text
pg_dump thingsboard --host=localhost --username postgres -W > thingsboard.dump
```
Then, backup the /etc/thingsboard/conf directory:
```text
sudo tar cvfz tb.tar.gz /etc/thingsboard/conf/*
```
Copy thingsboard.dump and tb.tar.gz to the new computer. On the new computer, with a fresh install of Thingsboard as per the posted instructions:

Stop Thingsboard, 
```text
sudo service thingsboard stop
```
then:
```text
sudo su postgres
```
dropdb thingsboard # this removes the database
```text
createdb -T template0 thingsboard
```
```text
psql thingsboard -U postgres -W < thingsboard.dump
```
Copy the /etc config files:
```text
cd /
```
```text
sudo tar xvfz /home/ubuntu/tb.tar.gz
```
Start Thingsboard.
```text
sudo service thingsboard start
```
