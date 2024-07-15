import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_contract extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_contract: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_auction_has_asset: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_auction_has_asset',
        key: 'id_auction_has_asset'
      }
    },
    contract_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    contract_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    total_amount: {
      type: DataTypes.INTEGER,
      allowNull: false
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    status_contract: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_contract',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_contract" },
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
        name: "id_auction_has_asset",
        using: "BTREE",
        fields: [
          { name: "id_auction_has_asset" },
        ]
      },
    ]
  });
  }
}
