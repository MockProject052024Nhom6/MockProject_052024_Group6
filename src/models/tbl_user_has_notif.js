import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_user_has_notif extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    id_notification: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_notification',
        key: 'id_notification'
      }
    }
  }, {
    sequelize,
    tableName: 'tbl_user_has_notif',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id" },
        ]
      },
      {
        name: "id_notification",
        using: "BTREE",
        fields: [
          { name: "id_notification" },
        ]
      },
      {
        name: "id_user",
        using: "BTREE",
        fields: [
          { name: "id_user" },
        ]
      },
    ]
  });
  }
}
