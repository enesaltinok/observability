#!/bin/sh
cd application/
for d in */; 
	do(cd "$d" && ./mvnw clean package);
done
