# dev-toolkit
Application to make developer life a little bit easier. 

# What is it ?
In dev-toolkit You can create presets to easily execute commands from UI. 
# Configuration file
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

