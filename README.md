# dev-toolkit
Application to make developer life a little bit easier. 

# What is it ?
In dev-toolkit You can create presets to easily execute commands from UI. 
# Configuration file
The configuration file contains three sections: applications, parameters and presets.
Each one is a map of name of item and its properties.

**Important !** Configuration examples are ready to download and testing up in examples/ directory  

## Application
Application defines a binary file on Your computer. 
* path - path to binary file localized on Your hdd, You can also use ${parameter-name} syntax here

## Parameter
Parameter defines some kind of variable which can be used later in preset. 
You can use parameter by using ${parameter-name} syntax
* type - type of variable, at the moment only string is supported
* default - default value of parameter
* values - optional, list of strings. If defined, combobox will be used as input control in UI
* hidden - if set to true, parameter will not be visible in UI, but will be accessible via ${parameter-name} syntax

## Preset
Preset defines an application connected with command line and parameters. Each preset uses one application.
* application - must be existing name of one of configured applications
* cmd - arguments for applications, ${parameter-name} can be used here. 

```javascript
{
  "applications": {
    "Maven": {
      "path": "mvn"
    }
  },
  "parameters": {
    "project-home": {
      "type": "string",
      "default": "/home/mint/work/git/dev-toolkit/"
    },
    "distribution": {
      "type": "string",
      "default": "C",
      "values": [
        "A",
        "B",
        "C"
      ]
    },
    "profile": {
      "type": "string",
      "default": "local",
      "values": [
        "dev",
        "local",
        "test"
      ]
    }
  },
  "presets": {
    "build": {
      "application": "Maven",
      "cmd": "-f ${project-home} clean install"
    },
    "clean": {
      "application": "Maven",
      "cmd": "-f ${project-home} clean"
    }
  }
}
```
# Screenshot
![Main Window screenshot](https://raw.githubusercontent.com/AdamTomaja/dev-toolkit/master/main-window.png)

