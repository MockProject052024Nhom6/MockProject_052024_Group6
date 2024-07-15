import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_appraisers extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_appraiser: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    experiences: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    specialized: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    status_appraiser: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    }
  }, {
    sequelize,
    tableName: 'tbl_appraisers',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_appraiser" },
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
