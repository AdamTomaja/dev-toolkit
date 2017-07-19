# dev-toolkit
Application to make developer life a little bit easier. 

# What is it ?
In dev-toolkit You can create presets to easily execute commands from UI. 

# Latest release download link
* [![Build Status](https://travis-ci.org/AdamTomaja/dev-toolkit.svg?branch=master)](https://travis-ci.org/AdamTomaja/dev-toolkit)
* [Download](https://github.com/AdamTomaja/dev-toolkit/releases/latest)

# Screenshot
![Main Window screenshot](https://raw.githubusercontent.com/AdamTomaja/dev-toolkit/master/screenshots/dev-toolkit-2.png)

# Configuration file
The configuration file contains three sections: applications, parameters and presets.
Each one is a map of name of item and its properties.
**Only XML configuration format is supported !** 
XSD file for configuration can be found in [configuration.xsd](app/src/main/xsd/configuration.xsd) file. 

**Important !** Configuration examples are ready to download and testing up in examples/ directory  

## Application
Application defines a binary file on Your computer. 
* path - path to binary file localized on Your hdd, You can also use ${parameter-name} syntax here

## Group
It`s possible to define list of groups with descriptions. For example when You need to order Your groups. 

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
* description - string - descriptive information about preset
* qtoolbox - boolean - if set to true, this preset will be displayed in the quick tool bar

## Child preset in compound one
* preset - string - name of preset
* ignorable - boolean - if set to true, this preset can exit with status different than 0 and compound preset will be continued. Example in examples/compound.json

will be executed

## Script
It`s possible to write scripts in JavaScript. A script is a piece of code executed before 
command invoke. 
A full example of script usage is available in examples directory.

```xml
<script name="script-name">
    <code>
        var read = api.getParameter("parameter-name"); // Reads a parameter value
        api.setParameter("parameter-name", read); // Writes a parameter value
        api.println("Hello World!"); // Prints line to job console
    </code>
</script>
```

# Plugins
CyderCode Dev-Toolkit is universal application so it is possible to write plugins for it. 
Example plugin for dev-toolkit can be found in **plugin-example** directory.
## How to install plugin
The only thing You have to do to run plugin is to place .jar file next to dev-toolkit in the same directory or child directories.