#!/usr/bin/env bash
mvn liquibase:update\
    -Denv=dev\
    -Dliquibase.url="jdbc:mysql://127.0.0.1:3306/?user=root"