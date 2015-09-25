#!/bin/bash

mvn clean install &&\
mv target/*dependencies.jar CyberNanites2000.jar &&\
echo "Installation completed"
