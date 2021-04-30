FROM mongo:latest

COPY seed-database.json /seed-database.json

CMD mongoimport --host mongodb \
                --db trails \
                --collection trails \
                --authenticationDatabase admin \
                --username root \
                --password rootpassword \
                --type json \
                --drop --file /seed-database.json \
                --jsonArray