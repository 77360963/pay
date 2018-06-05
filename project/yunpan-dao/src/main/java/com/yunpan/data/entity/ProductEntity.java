package com.yunpan.data.entity;

import java.util.Date;

public class ProductEntity {
    /**
     * <pre>
     * 商品ID
     * 表字段 : t_product.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 商户id
     * 表字段 : t_product.merchant_id
     * </pre>
     */
    private Long merchantId;

    /**
     * <pre>
     * 商品名称
     * 表字段 : t_product.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 商品图片
     * 表字段 : t_product.image
     * </pre>
     */
    private String image;

    /**
     * <pre>
     * 市场商品价格
     * 表字段 : t_product.market_price
     * </pre>
     */
    private Integer marketPrice;

    /**
     * <pre>
     * 现卖商品价格
     * 表字段 : t_product.sell_price
     * </pre>
     */
    private Integer sellPrice;

    /**
     * <pre>
     * 商品描述
     * 表字段 : t_product.descriptors
     * </pre>
     */
    private String descriptors;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_product.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_product.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_product.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：商品ID
     * 表字段：t_product.id
     * </pre>
     *
     * @return t_product.id：商品ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：商品ID
     * 表字段：t_product.id
     * </pre>
     *
     * @param id
     *            t_product.id：商品ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：商户id
     * 表字段：t_product.merchant_id
     * </pre>
     *
     * @return t_product.merchant_id：商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * <pre>
     * 设置：商户id
     * 表字段：t_product.merchant_id
     * </pre>
     *
     * @param merchantId
     *            t_product.merchant_id：商户id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * <pre>
     * 获取：商品名称
     * 表字段：t_product.name
     * </pre>
     *
     * @return t_product.name：商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 设置：商品名称
     * 表字段：t_product.name
     * </pre>
     *
     * @param name
     *            t_product.name：商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 获取：商品图片
     * 表字段：t_product.image
     * </pre>
     *
     * @return t_product.image：商品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * <pre>
     * 设置：商品图片
     * 表字段：t_product.image
     * </pre>
     *
     * @param image
     *            t_product.image：商品图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * <pre>
     * 获取：市场商品价格
     * 表字段：t_product.market_price
     * </pre>
     *
     * @return t_product.market_price：市场商品价格
     */
    public Integer getMarketPrice() {
        return marketPrice;
    }

    /**
     * <pre>
     * 设置：市场商品价格
     * 表字段：t_product.market_price
     * </pre>
     *
     * @param marketPrice
     *            t_product.market_price：市场商品价格
     */
    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * <pre>
     * 获取：现卖商品价格
     * 表字段：t_product.sell_price
     * </pre>
     *
     * @return t_product.sell_price：现卖商品价格
     */
    public Integer getSellPrice() {
        return sellPrice;
    }

    /**
     * <pre>
     * 设置：现卖商品价格
     * 表字段：t_product.sell_price
     * </pre>
     *
     * @param sellPrice
     *            t_product.sell_price：现卖商品价格
     */
    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * <pre>
     * 获取：商品描述
     * 表字段：t_product.descriptors
     * </pre>
     *
     * @return t_product.descriptors：商品描述
     */
    public String getDescriptors() {
        return descriptors;
    }

    /**
     * <pre>
     * 设置：商品描述
     * 表字段：t_product.descriptors
     * </pre>
     *
     * @param descriptors
     *            t_product.descriptors：商品描述
     */
    public void setDescriptors(String descriptors) {
        this.descriptors = descriptors == null ? null : descriptors.trim();
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_product.created_time
     * </pre>
     *
     * @return t_product.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_product.created_time
     * </pre>
     *
     * @param createdTime
     *            t_product.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_product.updated_time
     * </pre>
     *
     * @return t_product.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_product.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_product.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_product.status
     * </pre>
     *
     * @return t_product.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_product.status
     * </pre>
     *
     * @param status
     *            t_product.status：正常：1；删除：0
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductEntity other = (ProductEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getMarketPrice() == null ? other.getMarketPrice() == null : this.getMarketPrice().equals(other.getMarketPrice()))
            && (this.getSellPrice() == null ? other.getSellPrice() == null : this.getSellPrice().equals(other.getSellPrice()))
            && (this.getDescriptors() == null ? other.getDescriptors() == null : this.getDescriptors().equals(other.getDescriptors()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getMarketPrice() == null) ? 0 : getMarketPrice().hashCode());
        result = prime * result + ((getSellPrice() == null) ? 0 : getSellPrice().hashCode());
        result = prime * result + ((getDescriptors() == null) ? 0 : getDescriptors().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", name=").append(name);
        sb.append(", image=").append(image);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", sellPrice=").append(sellPrice);
        sb.append(", descriptors=").append(descriptors);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}