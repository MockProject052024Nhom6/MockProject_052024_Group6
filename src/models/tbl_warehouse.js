import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_warehouse extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_warehouse: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    warehouse_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    address_warehouse: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    asset_placement: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_warehouse',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_warehouse" },
        ]
      },
    ]
  });
  }
}
