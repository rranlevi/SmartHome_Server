<img src="https://github.com/user-attachments/assets/27c11120-34d4-4fed-8ef0-769a8c52b0fd" alt="alt text" width="480" height="640">|

# Smart Home Server

## Overview
This This project is a Smart Home Server implementation that facilitates the management and control of various household smart devices. The system is designed with a clear and structured architecture that dynamically generates the user interface (UI) on the client side, based on the devices available and their states. The server not only handles the logic and data processing for these devices but also ensures that the user's selection of devices is saved and can be retrieved for future sessions.

## Key Features

### 1. Dynamic UI Generation
The server is responsible for generating the client-side UI dynamically. This UI is constructed based on the current state and list of household devices connected to the server. By centralizing the UI generation, the server ensures that any updates or changes in the device configurations are immediately reflected on the client side without requiring manual updates to the UI.

### 2. Household Devices Management
The server supports a variety of household devices, each modeled and controlled through a well-defined class hierarchy. Devices can range from simple switches and sliders to more complex components like camera streams. The architecture allows for easy addition of new device types as needed.

### 3. Saving and Retrieving User Selections
The system is designed to persist user selections. When a user selects a device or configures a specific setting, this information is saved by the server. On subsequent connections or sessions, the saved state is retrieved and presented to the user, allowing for a seamless experience across different sessions.

## Architectural Design

The system follows a modular design, with each component playing a specific role:

- **Device Class**: The base class for all devices, containing common attributes such as `deviceName` and `description`.
- **HouseholdDevice Class**: Extends the `Device` class, adding additional attributes like `deviceController`, `deviceData`, and `deviceRoom`.
- **DeviceAction and Widget Classes**: Handle specific actions and UI elements associated with devices. These classes ensure that each device can interact with the user interface seamlessly.
- **Controllers**: The `DeviceActionController` and `DeviceDataController` manage the actions and data related to devices. They ensure that commands and data flows are handled correctly and are kept in sync with the server.
- **Info and DeviceInfo Classes**: Store information related to devices, which is used to generate the UI and manage device states.

## How to Use

1. **Setup the Server**: Clone the repository and follow the setup instructions to start the server.
2. **Connect Devices**: Add your household devices to the system by configuring them in the server's configuration file.
3. **Access the Client UI**: Open the client-side application in a web browser. The UI will be generated dynamically based on the devices configured on the server.
4. **Save and Retrieve Selections**: Any selections or configurations made through the UI will be automatically saved by the server. When you reconnect to the system, your previous selections will be loaded.

Walkthrough Videos - 

https://github.com/user-attachments/assets/b9c1207e-9106-49b1-aa6d-f29316a56a27
https://github.com/user-attachments/assets/7e1736d3-a2aa-4a8b-a2a4-c7be8627e724
https://github.com/user-attachments/assets/6dc8d1a9-f46e-4097-a9c7-38305292d270




## Project Requirements

- **Support for More Device Types**: The system can be extended to support a broader range of smart home devices.
- **Enhanced Security**: Implement additional security measures to protect user data and device configurations.
- **Mobile Application**: Develop a dedicated mobile app for easier access and control of the smart home system.

## Contributing

Contributions to this project are welcome! If you have any ideas for new features or improvements, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

## Contact

For any questions or support, feel free to contact the project maintainer at [email@example.com].

---
