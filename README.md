# UML References
<img src="https://github.com/rranlevi/SmartHome_Server/blob/master/Client_UML.png" alt="alt text" width="640" height="480">

# Smart Home Server

## Overview
This project is a Smart Home Server and Client implementation that facilitates the management and control of various household smart devices. The system is designed with a structured architecture that dynamically generates the user interface (UI) on the client side, based on the devices available and their states. The client fetching the devices data from the server, the server creates a mocking devices - but the project designed in a way that every data or action on a devices is a REST URL path, GET for data and POST for actions, in order to easily deploy to a real life scenario where the edge devices are real devices.

## Key Features

### 1. Dynamic UI Generation
The server is responsible for generating the client-side UI dynamically. This UI is constructed based on the current state and list of household devices connected to the server. By centralizing the UI generation, the server ensures that any updates or changes in the device configurations are immediately reflected on the client side without requiring manual updates to the UI.

### 2. Household Devices Management
The server supports a variety of household devices, each modeled and controlled through a well-defined class hierarchy. Devices can range from simple switches and sliders to more complex components like camera streams. The architecture allows for easy addition of new device types as needed.

### 3. Saving and Retrieving User Selections
The system is designed to persist user selections. When a user selects a device or configures a specific setting, this information is saved by the server. On subsequent connections or sessions, the saved state is retrieved and presented to the user, allowing for a seamless experience across different sessions.

## Architectural Design
<img src="https://github.com/user-attachments/assets/27c11120-34d4-4fed-8ef0-769a8c52b0fd" alt="alt text" width="480" height="640">|

The system follows a modular design, with each component playing a specific role:

- **Device Class**: The base class for all devices, containing common attributes such as deviceName and description.
- **HouseholdDevice Class**: Extends the Device class, designed so we can represent every new smart home device in the future, every device have dataController for device realtime data and actionController for sending commands:
  - **Controllers**: The DeviceActionController and DeviceDataController manage the actions and data related to devices. They ensure that commands and data flows are handled correctly and are kept in sync with the server.
- **Info and DeviceInfo Classes**: Store information related to devices, which is used to generate the UI and manage device states.
- **DeviceAction and Widget Classes**: Handle specific actions and UI elements associated with devices. These classes ensure that each device can interact with the user interface seamlessly, and it is the key-feature for scalability in our project (easy support for new devices in the future).


## How to Use

1. **Setup**: Clone the client and server repositories, first run the server then the client as shown in the video below.
2. **Add Devices**: Add your household devices by opening a window with the server-connected devices through the button.
3. **Actions & Data**: After adding devices from the server enter the Actions & Data page, send data to the devices through the server, and see live data.

**Walkthrough Videos**:



https://github.com/user-attachments/assets/98e5a308-b46c-4a67-9109-ac508599a38f






https://github.com/user-attachments/assets/52f4eb5c-b1d1-44f4-a443-f944792480af




## Project Requirements

- **Support for More Device Types**: The system can be extended to support a broader range of smart home devices.
- **Enhanced Security**: Implement additional security measures to protect user data and device configurations.
- **Mobile Application**: Develop a dedicated mobile app for easier access and control of the smart home system.

## Credits

This project was made possible by the contributions of the following individuals:

- **Ran Levi**
- **Harel Markel**
- **Alex Makarov**
- **Ron Yamin**

## Licensing

Copyright (c) 2024 Ran Levi, Harel Markel, Alex Makarov, Ron Yamin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

---
