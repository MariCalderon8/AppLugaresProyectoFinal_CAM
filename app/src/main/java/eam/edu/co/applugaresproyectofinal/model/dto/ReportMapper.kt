package eam.edu.co.applugaresproyectofinal.model.dto

import eam.edu.co.applugaresproyectofinal.model.Report

fun Report.toDTO(): ReportDTO =
    ReportDTO(
        id = id,
        userId = userId,
        subject = subject,
        description = description
    )

fun ReportDTO.toModel(): Report =
    Report(
        id = id,
        userId = userId,
        subject = subject,
        description = description
    )
