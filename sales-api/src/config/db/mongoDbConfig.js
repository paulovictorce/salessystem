import mongoose from "mongoose"

import { MONGO_DB_URL } from  "../secrets/secrets";

export function connect() {
    mongoose.connect(MONGO_DB_URL, {
        useNewUrlParser: true,
    });
    mongoose.connection.on('connected', function() {
        console.info("Application connected on DB successfully!");
    });
    mongoose.connection.on('error', function() {
        console.error("Application not connected on DB successfully!");
    });
}