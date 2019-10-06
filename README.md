# Three Pillars of Observability

This project aims to show you three pillars of observability in action.  

- Logs: ELK Stack
- Metrics: Prometheus & Grafana
- Traces: Jaeger

## Presentation
https://www.slideshare.net/EnesAltnok/observability-180267836

## Architecture

![Architecture](https://github.com/enesaltinok/observability/blob/master/Observability.png)

## Build
```
./build_java_applications.sh
```

> Important : 
You need to run this build script every time you change a code in java applications.

## Run
```
docker-compose up
```

> Important : 
Do not use this docker configuration in production

## Test
Open Grafana dashboard, run Observability.jmx jmeter template and watch how metrics are changing. 

Run this curl command for a single request:  
```
curl -d '{"firstName":"Enes", "lastName":"Altınok", "emailAddress": "enes.altinok@emlakjet.com"}' -H "Content-Type: application/json" -X POST "http://127.0.0.1:8081/user"
```
  
