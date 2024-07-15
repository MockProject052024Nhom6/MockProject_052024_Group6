import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_user_has_auction extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_auction: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_auction',
        key: 'id_auction'
      }
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    deposits: {
      type: DataTypes.FLOAT,
      allowNull: true
    },
    deposits_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    status_user_has_auc: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_user_has_auction',
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
        name: "id_user",
        using: "BTREE",
        fields: [
          { name: "id_user" },
        ]
      },
      {
        name: "id_auction",
        using: "BTREE",
        fields: [
          { name: "id_auction" },
        ]
      },
    ]
  });
  }
}
