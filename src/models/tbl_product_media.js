import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_product_media extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_media: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_asset: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_asset',
        key: 'id_asset'
      }
    },
    link: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    type: {
      type: DataTypes.STRING(255),
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
    tableName: 'tbl_product_media',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_media" },
        ]
      },
      {
        name: "id_asset",
        using: "BTREE",
        fields: [
          { name: "id_asset" },
        ]
      },
    ]
  });
  }
}
