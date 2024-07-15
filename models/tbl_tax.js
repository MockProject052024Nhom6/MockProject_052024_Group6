import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_tax extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_tax: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    tax_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    tax_type: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    tax_rate: {
      type: DataTypes.FLOAT,
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
    tableName: 'tbl_tax',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_tax" },
        ]
      },
    ]
  });
  }
}
