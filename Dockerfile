from groovy:3.0-jre16

ADD checkOpenedPorts.groovy /

ENV PORTS_TO_CHECK  "0.0.0.0:22"
CMD [ "groovy", "/checkOpenedPorts.groovy" ]

