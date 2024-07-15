import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_asset extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_asset: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    asset_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    description: {
      type: DataTypes.TEXT,
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    size: {
      type: DataTypes.FLOAT,
      allowNull: true
    },
    origin: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    property_status: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    quantity: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    status_faultfinding: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status_asset: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    id_user_winner: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    id_seller: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    id_warehouse: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_warehouse',
        key: 'id_warehouse'
      }
    },
    id_appraiser: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_appraisers',
        key: 'id_appraiser'
      }
    },
    id_category: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_category_asset',
        key: 'id_category'
      }
    }
  }, {
    sequelize,
    tableName: 'tbl_asset',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_asset" },
        ]
      },
      {
        name: "id_user_winner",
        using: "BTREE",
        fields: [
          { name: "id_user_winner" },
        ]
      },
      {
        name: "id_seller",
        using: "BTREE",
        fields: [
          { name: "id_seller" },
        ]
      },
      {
        name: "id_warehouse",
        using: "BTREE",
        fields: [
          { name: "id_warehouse" },
        ]
      },
      {
        name: "id_appraiser",
        using: "BTREE",
        fields: [
          { name: "id_appraiser" },
        ]
      },
      {
        name: "id_category",
        using: "BTREE",
        fields: [
          { name: "id_category" },
        ]
      },
    ]
  });
  }
}
