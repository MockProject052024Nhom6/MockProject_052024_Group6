import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_fee extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_fee: {
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
    fee_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    cost: {
      type: DataTypes.FLOAT,
      allowNull: true
    },
    payment_status: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    payment_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_fee',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_fee" },
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
