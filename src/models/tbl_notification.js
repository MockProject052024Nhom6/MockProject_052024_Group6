import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_notification extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_notification: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    content: {
      type: DataTypes.TEXT,
      allowNull: true
    },
    status: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_notification',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_notification" },
        ]
      },
    ]
  });
  }
}