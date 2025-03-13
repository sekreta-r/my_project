package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.entity.ParcelEntity;
import ru.hpclab.hl.module1.model.Parcel;

public class ParcelMapper {
    private ParcelMapper() {
    }

    public static ParcelEntity parcelToEntity(Parcel parcel) {
        if (parcel == null) return null;

        return new ParcelEntity(
                parcel.getId(),
                parcel.getWeight(),
                parcel.getDimensions(),
                parcel.getDestinationAddress()
        );
    }

    public static Parcel entityToParcel(ParcelEntity parcelEntity) {
        if (parcelEntity == null) return null;

        return new Parcel(
                parcelEntity.getId(),
                parcelEntity.getWeight(),
                parcelEntity.getDimensions(),
                parcelEntity.getDestinationAddress()
        );
    }
}
