# About

This is a prometheus exporter which checks if some ports of an host are opened.

It is implemented as a groovy script, and makes use of the
[https://sparkjava.com](sparkjava) framework.

# configuration

set the variable `PORTS_TO_CHECK` accordingly, see next paragraph
for an example in a docker/rancher compose.

# setup

```
version: '2'
services:
  check-opened-ports:
    image: pmauduit/opened-ports-prometheus-exporter
    environment:
      PORTS_TO_CHECK: 0.0.0.0:2222,0.0.0.0:2223,0.0.0.0:2224
    network_mode: host
    labels:
      io.rancher.scheduler.affinity:host_label: application=true
      io.rancher.container.pull_image: always
      prometheus_port: '9200'
```

This specification will check that the port 2222, 2223, 2224 on the rancher
host labeled `application=true` has its ports opened on the address `0.0.0.0`.
