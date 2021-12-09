const env = process.env;

export const MONGO_DB_URL = env.MONGO_DB_URL
  ? env.MONGO_DB_URL
  : "mongodb://admin:mongo@localhost/sales";
  
export const API_SECRET = env.API_SECRET
  ? env.API_SECRET
  : "YXV0aC1hcGktYXBwLXNhbGVzLXN5c3RlbS1qYXZhc2NyaXB0LW5vZGVqcy1qYXZhLXJhYmJpdG1x";
