FROM azul/zulu-openjdk:11

EXPOSE 8380/tcp
ARG version='1.0.0'
LABEL com.davidparry.leaky.schema-version="1.0.0" \
com.davidparry.name="Leaky Memory Service" \
com.davidparry.description="Provide a Leaky Spring Boot Application to Test with!" \
com.lexipol.version="${version}"

COPY --chown=755 ["app-entrypoint.sh", "boot.jar", "/"]

ENTRYPOINT ["/app-entrypoint.sh"]