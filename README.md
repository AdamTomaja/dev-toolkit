# dev-toolkit
Application to make developer life a little bit easier. 

# What is it ?
In dev-toolkit You can create presets to easily execute commands from UI. 

# Latest release download link
* [![Build Status](https://travis-ci.org/AdamTomaja/dev-toolkit.svg?branch=master)](https://travis-ci.org/AdamTomaja/dev-toolkit)
* [Download](https://github.com/AdamTomaja/dev-toolkit/releases/latest)

# Screenshot
![Main Window screenshot](https://raw.githubusercontent.com/AdamTomaja/dev-toolkit/master/screenshots/groups.png)


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
* group - string - if set, the parameter will be displayed in group, can be any string

## Preset
Preset defines an application connected with command line and parameters. Each preset uses one application.
* application - must be existing name of one of configured applications
* cmd - arguments for applications, ${parameter-name} can be used here. 
* group - string - if set, the preset will be displayed in group, can be any string
* presets - list of preset names or maps of compound presets - if set, preset will be interpreted as compound. That means all presets from the list.

## Child preset in compound one
* preset - string - name of preset
* ignorable - boolean - if set to true, this preset can exit with status different than 0 and compound preset will be continued. Example in examples/compound.json

will be executed

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
    },
    "Build and clean": {
        "presets": [
          {
            "preset":"clean", 
            "ignorable": true
            }, 
          "build"
        ]
    }
  }
}
```
# Examples
![Main Window screenshot](https://raw.githubusercontent.com/AdamTomaja/dev-toolkit/master/screenshots/git-example.png)
![Main Window screenshot](https://raw.githubusercontent.com/AdamTomaja/dev-toolkit/master/screenshots/maven-example.png)
![Main Window screenshot](https://raw.githubusercontent.com/AdamTomaja/dev-toolkit/master/screenshots/main-window.png)

