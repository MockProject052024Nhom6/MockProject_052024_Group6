import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_role extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_role: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    role_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    description: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_role',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_role" },
        ]
      },
    ]
  });
  }
}
