package com.example.composepoc.utils

import java.net.MalformedURLException

// Commercial
const val PGM_IC_ED_ID = "IC,IC-ED,ON"
const val PGM_IC_EH_ID = "IC,IC-EH,ON"
const val PGM_IC_EN_ID = "IC,IC-EN,ON"
const val PGM_IC_ND_ID = "IC,IC-ND,ON"
const val PGM_IC_NH_ID = "IC,IC-NH,ON"
const val PGM_IC_MO_ON_ID = "IC,IC-MO,ON"

// Medicare
const val PGM_IC_MO_ID = "IC,IC-M0,OS"
const val PGM_IC_M3_ID = "IC,IC-M3,OS"
const val PGM_IC_SJ_ID = "IC,IC-SJ,OS"

enum class ProgramIDs(val listOfPgmIds: List<String>) {
    COMMERCIAL(
        listOf(
            PGM_IC_ED_ID,
            PGM_IC_EH_ID,
            PGM_IC_EN_ID,
            PGM_IC_ND_ID,
            PGM_IC_NH_ID,
            PGM_IC_MO_ON_ID,
        ),
    ),
    MEDICARE(listOf(PGM_IC_MO_ID, PGM_IC_M3_ID, PGM_IC_SJ_ID)),
}

/** * @param planType - playType passed as input. * @param loggedInUserPgmCarId - loggedInUserPgmCarId passed as input, concat the carrier and program. * @see PpoPlansUseCaseImpl.getPlanType - for implementation detail */
fun validProgramID(planType: String, loggedInUserPgmCarId: String): Boolean {
    return getProgramID(planType)?.listOfPgmIds?.contains(loggedInUserPgmCarId) ?: false
}

fun getProgramID(planType: String): ProgramIDs? {
    return try {
        ProgramIDs.valueOf(planType)
    } catch (exception: MalformedURLException) {
        return null
    }
}
