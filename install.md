*Quick links :*
***
[Home](/README.md) 
- [**1. Setup Gateway Mesh Network**](/1_mesh_network_gateway.md)
- [2. Setup Node/Client Mesh Network](/2_mesh_network_node.md)
- [3. Setup Bluetooth Service](/3_bluetooth_service.md)

## batman-adv

To create the mesh network on the Raspberry Pi we are using [batman-adv](https://www.open-mesh.org/projects/open-mesh/wiki), which is part of the standard linux kernel.  We are going to configure the batman-adv kernel module to take control of the WiFi interface **wlan0** and create a mesh network over WiFi.  Batman-adv will then create a new interface **bat0** to allow the Pi to send network traffic over the mesh network.  This will be explored more in the section on [network access](ROUTE.md)

You need to complete the following steps on all the Raspberry Pis that you want to be part of the mesh network, including the gateway and bridge nodes.  You can choose to use [headerless setup](/additionalResources/HEADERLESS_SETUP.md) or a keyboard, mouse and monitor to access the Pi command line.

## Create the SD card and perform initial setup

1. Download the latest Raspberry Pi Imager from [[raspberrypi.org](https://www.raspberrypi.com/software/)].  Choose the Raspbian 64bit Lite version.
2. Default configuration:
   ```
   - Hostname: iotgateway - for the main gateway. iotnode-##### for the additional nodes (5 alphanumeric characters)
   - Username: iotadmin
   - Password: raspberry
   - Mesh key: 10 characters SHA256 hash.  "To be or not to be, that is the question" is 14e3a54aa3
   - Enable SSH: SSH server is enabled with username/password authentication
   ```
4. Flash the image to an SD card suitable for your Raspberry Pi.  Instructions are available [here](https://www.raspberrypi.org/documentation/installation/installing-images/README.md) if needed.
    Eject the SD card from your operating system and remove the card from your computer.
5. Insert the SD card into the Raspberry Pi and then power on the Raspberry Pi.
6. Login to the pi with user **iotadmin** and password **raspberry**.  If using headerless setup then connect via [ssh](/additionalResources/COMMAND_LINE_ACCESS.md).  The hostname on first boot is **raspberrypi.local**.  
7. On the Raspberry Pi command line issue the command

    ```text
   sudo raspi-config
    ```

    and then go through and change the following settings:
    - Change the user password (don't forget it, as you will need it every time you remotely connect to the Pi)
    - Network Options - Hostname
    - Localisation Options - set Locale, Timezone and WiFi country to match your location
    - Network Option - WiFi.  If your pi is not connected to the internet already, use this option to setup WiFi connectivity to ensure your Pi has access to the internet
    - interfacing Options - SSH, ensure SSH server is enabled

    Exit raspi-config, don't reboot yet.
9. Issue command

    ```text
   sudo apt-get update && sudo apt-get upgrade -y
    ```
11. Reboot the Raspberry Pi with command

    ```text
    sudo reboot -n
    ```
