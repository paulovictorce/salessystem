import Sequelize from "sequelize";

const sequelize = new Sequelize("auth-db", "admin", "postgres", {
  host: "localhost",
  dialect: "postgres",
  quoteIdentifiers: false,
  define: {
    syncOnAssociation: true,
    timestamps: false,
    underscored: true,
    underscoredAll: true,
    freezeTableName: true,
  },
});

sequelize
  .authenticate()
  .then(() => {
    console.info("Database AUTH-DB connection has been stablished.");
  })
  .catch((err) => {
    console.error("Unable to connect to database AUTH-DB");
    console.error(err.message);
  });

export default sequelize;
