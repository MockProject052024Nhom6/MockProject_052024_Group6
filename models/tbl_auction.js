import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_auction extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_auction: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    auction_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    address: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    number_of_participants: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    start_time: {
      type: DataTypes.DATE,
      allowNull: true
    },
    end_time: {
      type: DataTypes.DATE,
      allowNull: true
    },
    type_auction: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    },
    id_host: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    status_auction: {
      type: DataTypes.STRING(255),
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_auction',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_auction" },
        ]
      },
      {
        name: "id_host",
        using: "BTREE",
        fields: [
          { name: "id_host" },
        ]
      },
    ]
  });
  }
}
