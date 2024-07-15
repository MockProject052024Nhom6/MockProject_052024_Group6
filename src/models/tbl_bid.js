import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_bid extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_bid: {
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
    id_auction_has_asset: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_auction_has_asset',
        key: 'id_auction_has_asset'
      }
    },
    bid_amount: {
      type: DataTypes.FLOAT,
      allowNull: true
    },
    bid_time: {
      type: DataTypes.DATE,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_bid',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_bid" },
        ]
      },
      {
        name: "id_auction_has_asset",
        using: "BTREE",
        fields: [
          { name: "id_auction_has_asset" },
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
