import _sequelize from 'sequelize';
const { Model, Sequelize } = _sequelize;

export default class tbl_transaction_history extends Model {
  static init(sequelize, DataTypes) {
  return super.init({
    id_trans_history: {
      autoIncrement: true,
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
      references: {
        model: 'tbl_user',
        key: 'id_user'
      }
    },
    transaction_amount: {
      type: DataTypes.FLOAT,
      allowNull: true
    },
    content: {
      type: DataTypes.TEXT,
      allowNull: true
    },
    sender_account_number: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    bank: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    account_owner_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    receiver_name: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    recipient_account_number: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    status_transaction: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    note: {
      type: DataTypes.STRING(255),
      allowNull: true
    },
    day_trading: {
      type: DataTypes.DATEONLY,
      allowNull: true
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: true
    }
  }, {
    sequelize,
    tableName: 'tbl_transaction_history',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id_trans_history" },
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
