import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_category_asset extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_category: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    category_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_category_asset',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_category" },
        ]
      },
    ]
  });
  }
}
