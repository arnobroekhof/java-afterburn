FROM java:openjdk-8-alpine

RUN apk add --no-cache curl && \
    curl -sfL \
    https://github.com/openfaas-incubator/of-watchdog/releases/download/0.1.0/of-watchdog > /usr/bin/fwatchdog && \
    chmod +x /usr/bin/fwatchdog

WORKDIR /root/

COPY java-afterburn-0.1.jar java-afterburn-0.1.jar

WORKDIR /root/

ENV cgi_headers="true"

ENV write_debug=true
ENV read_debug=true

ENV fprocess="java -jar java-afterburn-0.1.jar"
ENV afterburn=true
ENV mode=afterburn
RUN touch /tmp/.lock

HEALTHCHECK --interval=1s CMD [ -e /tmp/.lock ] || exit 1
EXPOSE 8080
CMD ["fwatchdog"]
