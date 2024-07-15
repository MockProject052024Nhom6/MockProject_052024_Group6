import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_inventory_transactions extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_inventory_trans: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_warehouse: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_warehouse',
        key: 'id_warehouse'
      }
    },
    import_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    export_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    description: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_inventory_transactions',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_inventory_trans" },
        ]
      },
      {
        name: "id_warehouse",
        using: "BTREE",
        fields: [
          { name: "id_warehouse" },
        ]
      },
    ]
  });
  }
}
