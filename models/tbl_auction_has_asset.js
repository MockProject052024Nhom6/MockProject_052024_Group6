import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_auction_has_asset extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_auction_has_asset: {
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
    id_asset: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_asset',
        key: 'id_asset'
      }
    },
    starting_price: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    present_price: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    auction_results: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_auction_has_asset',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_auction_has_asset" },
        ]
      },
      {
        name: "id_asset",
        using: "BTREE",
        fields: [
          { name: "id_asset" },
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
