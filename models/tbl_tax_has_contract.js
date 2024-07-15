import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_tax_has_contract extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_tax_contract: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_contract: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_contract',
        key: 'id_contract'
      }
    },
    id_tax: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_tax',
        key: 'id_tax'
      }
    },
    tax_amount: {
      type: DataTypes.INTEGER,
      allowNull: false
    }
  }, {
    sequelize,
    tableName: 'tbl_tax_has_contract',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_tax_contract" },
        ]
      },
      {
        name: "id_contract",
        using: "BTREE",
        fields: [
          { name: "id_contract" },
        ]
      },
      {
        name: "id_tax",
        using: "BTREE",
        fields: [
          { name: "id_tax" },
        ]
      },
    ]
  });
  }
}
