package dev.netanelbcn.kinderkit.Interfaces;

import dev.netanelbcn.kinderkit.Models.ImmunizationRecord;
import dev.netanelbcn.kinderkit.Models.Kid;

public interface IRCallback {
    void deleteClicked(ImmunizationRecord record, int position);
}
