import _sequelize from "sequelize";
const DataTypes = _sequelize.DataTypes;
import _tbl_appraisers from  "./tbl_appraisers.js";
import _tbl_asset from  "./tbl_asset.js";
import _tbl_auction from  "./tbl_auction.js";
import _tbl_auction_has_asset from  "./tbl_auction_has_asset.js";
import _tbl_bid from  "./tbl_bid.js";
import _tbl_category_asset from  "./tbl_category_asset.js";
import _tbl_contract from  "./tbl_contract.js";
import _tbl_fee from  "./tbl_fee.js";
import _tbl_information_account from  "./tbl_information_account.js";
import _tbl_inventory_transactions from  "./tbl_inventory_transactions.js";
import _tbl_notification from  "./tbl_notification.js";
import _tbl_product_media from  "./tbl_product_media.js";
import _tbl_role from  "./tbl_role.js";
import _tbl_tax from  "./tbl_tax.js";
import _tbl_tax_has_contract from  "./tbl_tax_has_contract.js";
import _tbl_transaction_history from  "./tbl_transaction_history.js";
import _tbl_user from  "./tbl_user.js";
import _tbl_user_has_auction from  "./tbl_user_has_auction.js";
import _tbl_user_has_notif from  "./tbl_user_has_notif.js";
import _tbl_user_has_role from  "./tbl_user_has_role.js";
import _tbl_warehouse from  "./tbl_warehouse.js";

export default function initModels(sequelize) {
  const tbl_appraisers = _tbl_appraisers.init(sequelize, DataTypes);
  const tbl_asset = _tbl_asset.init(sequelize, DataTypes);
  const tbl_auction = _tbl_auction.init(sequelize, DataTypes);
  const tbl_auction_has_asset = _tbl_auction_has_asset.init(sequelize, DataTypes);
  const tbl_bid = _tbl_bid.init(sequelize, DataTypes);
  const tbl_category_asset = _tbl_category_asset.init(sequelize, DataTypes);
  const tbl_contract = _tbl_contract.init(sequelize, DataTypes);
  const tbl_fee = _tbl_fee.init(sequelize, DataTypes);
  const tbl_information_account = _tbl_information_account.init(sequelize, DataTypes);
  const tbl_inventory_transactions = _tbl_inventory_transactions.init(sequelize, DataTypes);
  const tbl_notification = _tbl_notification.init(sequelize, DataTypes);
  const tbl_product_media = _tbl_product_media.init(sequelize, DataTypes);
  const tbl_role = _tbl_role.init(sequelize, DataTypes);
  const tbl_tax = _tbl_tax.init(sequelize, DataTypes);
  const tbl_tax_has_contract = _tbl_tax_has_contract.init(sequelize, DataTypes);
  const tbl_transaction_history = _tbl_transaction_history.init(sequelize, DataTypes);
  const tbl_user = _tbl_user.init(sequelize, DataTypes);
  const tbl_user_has_auction = _tbl_user_has_auction.init(sequelize, DataTypes);
  const tbl_user_has_notif = _tbl_user_has_notif.init(sequelize, DataTypes);
  const tbl_user_has_role = _tbl_user_has_role.init(sequelize, DataTypes);
  const tbl_warehouse = _tbl_warehouse.init(sequelize, DataTypes);

  tbl_asset.belongsTo(tbl_appraisers, { as: "id_appraiser_tbl_appraiser", foreignKey: "id_appraiser"});
  tbl_appraisers.hasMany(tbl_asset, { as: "tbl_assets", foreignKey: "id_appraiser"});
  tbl_auction_has_asset.belongsTo(tbl_asset, { as: "id_asset_tbl_asset", foreignKey: "id_asset"});
  tbl_asset.hasMany(tbl_auction_has_asset, { as: "tbl_auction_has_assets", foreignKey: "id_asset"});
  tbl_product_media.belongsTo(tbl_asset, { as: "id_asset_tbl_asset", foreignKey: "id_asset"});
  tbl_asset.hasMany(tbl_product_media, { as: "tbl_product_media", foreignKey: "id_asset"});
  tbl_auction_has_asset.belongsTo(tbl_auction, { as: "id_auction_tbl_auction", foreignKey: "id_auction"});
  tbl_auction.hasMany(tbl_auction_has_asset, { as: "tbl_auction_has_assets", foreignKey: "id_auction"});
  tbl_user_has_auction.belongsTo(tbl_auction, { as: "id_auction_tbl_auction", foreignKey: "id_auction"});
  tbl_auction.hasMany(tbl_user_has_auction, { as: "tbl_user_has_auctions", foreignKey: "id_auction"});
  tbl_bid.belongsTo(tbl_auction_has_asset, { as: "id_auction_has_asset_tbl_auction_has_asset", foreignKey: "id_auction_has_asset"});
  tbl_auction_has_asset.hasMany(tbl_bid, { as: "tbl_bids", foreignKey: "id_auction_has_asset"});
  tbl_contract.belongsTo(tbl_auction_has_asset, { as: "id_auction_has_asset_tbl_auction_has_asset", foreignKey: "id_auction_has_asset"});
  tbl_auction_has_asset.hasMany(tbl_contract, { as: "tbl_contracts", foreignKey: "id_auction_has_asset"});
  tbl_fee.belongsTo(tbl_auction_has_asset, { as: "id_auction_has_asset_tbl_auction_has_asset", foreignKey: "id_auction_has_asset"});
  tbl_auction_has_asset.hasMany(tbl_fee, { as: "tbl_fees", foreignKey: "id_auction_has_asset"});
  tbl_asset.belongsTo(tbl_category_asset, { as: "id_category_tbl_category_asset", foreignKey: "id_category"});
  tbl_category_asset.hasMany(tbl_asset, { as: "tbl_assets", foreignKey: "id_category"});
  tbl_tax_has_contract.belongsTo(tbl_contract, { as: "id_contract_tbl_contract", foreignKey: "id_contract"});
  tbl_contract.hasMany(tbl_tax_has_contract, { as: "tbl_tax_has_contracts", foreignKey: "id_contract"});
  tbl_user_has_notif.belongsTo(tbl_notification, { as: "id_notification_tbl_notification", foreignKey: "id_notification"});
  tbl_notification.hasMany(tbl_user_has_notif, { as: "tbl_user_has_notifs", foreignKey: "id_notification"});
  tbl_user_has_role.belongsTo(tbl_role, { as: "id_role_tbl_role", foreignKey: "id_role"});
  tbl_role.hasMany(tbl_user_has_role, { as: "tbl_user_has_roles", foreignKey: "id_role"});
  tbl_tax_has_contract.belongsTo(tbl_tax, { as: "id_tax_tbl_tax", foreignKey: "id_tax"});
  tbl_tax.hasMany(tbl_tax_has_contract, { as: "tbl_tax_has_contracts", foreignKey: "id_tax"});
  tbl_appraisers.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_appraisers, { as: "tbl_appraisers", foreignKey: "id_user"});
  tbl_asset.belongsTo(tbl_user, { as: "id_user_winner_tbl_user", foreignKey: "id_user_winner"});
  tbl_user.hasMany(tbl_asset, { as: "tbl_assets", foreignKey: "id_user_winner"});
  tbl_asset.belongsTo(tbl_user, { as: "id_seller_tbl_user", foreignKey: "id_seller"});
  tbl_user.hasMany(tbl_asset, { as: "id_seller_tbl_assets", foreignKey: "id_seller"});
  tbl_auction.belongsTo(tbl_user, { as: "id_host_tbl_user", foreignKey: "id_host"});
  tbl_user.hasMany(tbl_auction, { as: "tbl_auctions", foreignKey: "id_host"});
  tbl_bid.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_bid, { as: "tbl_bids", foreignKey: "id_user"});
  tbl_contract.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_contract, { as: "tbl_contracts", foreignKey: "id_user"});
  tbl_information_account.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_information_account, { as: "tbl_information_accounts", foreignKey: "id_user"});
  tbl_transaction_history.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_transaction_history, { as: "tbl_transaction_histories", foreignKey: "id_user"});
  tbl_user_has_auction.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_user_has_auction, { as: "tbl_user_has_auctions", foreignKey: "id_user"});
  tbl_user_has_notif.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_user_has_notif, { as: "tbl_user_has_notifs", foreignKey: "id_user"});
  tbl_user_has_role.belongsTo(tbl_user, { as: "id_user_tbl_user", foreignKey: "id_user"});
  tbl_user.hasMany(tbl_user_has_role, { as: "tbl_user_has_roles", foreignKey: "id_user"});
  tbl_asset.belongsTo(tbl_warehouse, { as: "id_warehouse_tbl_warehouse", foreignKey: "id_warehouse"});
  tbl_warehouse.hasMany(tbl_asset, { as: "tbl_assets", foreignKey: "id_warehouse"});
  tbl_inventory_transactions.belongsTo(tbl_warehouse, { as: "id_warehouse_tbl_warehouse", foreignKey: "id_warehouse"});
  tbl_warehouse.hasMany(tbl_inventory_transactions, { as: "tbl_inventory_transactions", foreignKey: "id_warehouse"});

  return {
    tbl_appraisers,
    tbl_asset,
    tbl_auction,
    tbl_auction_has_asset,
    tbl_bid,
    tbl_category_asset,
    tbl_contract,
    tbl_fee,
    tbl_information_account,
    tbl_inventory_transactions,
    tbl_notification,
    tbl_product_media,
    tbl_role,
    tbl_tax,
    tbl_tax_has_contract,
    tbl_transaction_history,
    tbl_user,
    tbl_user_has_auction,
    tbl_user_has_notif,
    tbl_user_has_role,
    tbl_warehouse,
  };
}
