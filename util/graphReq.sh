java -jar rsc-0.9.1.jar \
--stream \
--route=graphql \
--dataMimeType="application/graphql+json" \
--data='{"query":"subscription { employees { name } }" }' \
--debug tcp://localhost:9191