package io.github.vyo.strakh.model.agent

import io.github.vyo.strakh.goap.component.Agent
import io.github.vyo.strakh.model.game.State

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

abstract class Unit(val unit: bwapi.Unit) : Agent, State() {

    var position: bwapi.Position = bwapi.Position(-1, -1)
    var isAccelerating: Boolean = false
    var isAttacking: Boolean = false
    var isBeingConstructed: Boolean = false
    var isBeingGathered: Boolean = false
    var isBeingHealed: Boolean = false
    var isBlind: Boolean = false
    var isBraking: Boolean = false
    var isBurrowed: Boolean = false
    var isCarryingGas: Boolean = false
    var isCarryingMinerals: Boolean = false
    var isCloaked: Boolean = false
    var isCompleted: Boolean = false
    var isConstructing: Boolean = false
    var isDefenseMatrixed: Boolean = false
    var isDetected: Boolean = false
    var isEnsnared: Boolean = false
    var isFollowing: Boolean = false
    var isGatheringGas: Boolean = false
    var isGatheringMinerals: Boolean = false
    var isHallucination: Boolean = false
    var isHoldingPosition: Boolean = false
    var isIdle: Boolean = false
    var isInterruptible: Boolean = false
    var isInvincible: Boolean = false
    var isIrradiated: Boolean = false
    var isLifted: Boolean = false
    var isLoaded: Boolean = false
    var isLockedDown: Boolean = false
    var isMaelstrommed: Boolean = false
    var isMorphing: Boolean = false
    var isMoving: Boolean = false
    var isParasited: Boolean = false
    var isPatrolling: Boolean = false
    var isPlagued: Boolean = false
    var isPowered: Boolean = false
    var isRepairing: Boolean = false
    var isResearching: Boolean = false
    var isSieged: Boolean = false
    var isStartingAttack: Boolean = false
    var isStasised: Boolean = false
    var isStimmed: Boolean = false
    var isStuck: Boolean = false
    var isTraining: Boolean = false
    var isUnderAttack: Boolean = false
    var isUnderDarkSwarm: Boolean = false
    var isUnderDisruptionWeb: Boolean = false
    var isUnderStorm: Boolean = false
    var isUpgrading: Boolean = false
    var isVisible: Boolean = false

    override fun update() {
        position = unit.position
        isAccelerating = unit.isAccelerating
        isAttacking = unit.isAttacking
        isBeingConstructed = unit.isBeingConstructed
        isBeingGathered = unit.isBeingGathered
        isBeingHealed = unit.isBeingHealed
        isBlind = unit.isBlind
        isBraking = unit.isBraking
        isBurrowed = unit.isBurrowed
        isCarryingGas = unit.isCarryingGas
        isCarryingMinerals = unit.isCarryingMinerals
        isCloaked = unit.isCloaked
        isCompleted = unit.isCompleted
        isConstructing = unit.isConstructing
        isDefenseMatrixed = unit.isDefenseMatrixed
        isDetected = unit.isDetected
        isEnsnared = unit.isEnsnared
        isFollowing = unit.isFollowing
        isGatheringGas = unit.isGatheringGas
        isGatheringMinerals = unit.isGatheringMinerals
        isHallucination = unit.isHallucination
        isHoldingPosition = unit.isHoldingPosition
        isIdle = unit.isIdle
        isInterruptible = unit.isInterruptible
        isInvincible = unit.isInvincible
        isIrradiated = unit.isIrradiated
        isLifted = unit.isLifted
        isLoaded = unit.isLoaded
        isLockedDown = unit.isLockedDown
        isMaelstrommed = unit.isMaelstrommed
        isMorphing = unit.isMorphing
        isMoving = unit.isMoving
        isParasited = unit.isParasited
        isPatrolling = unit.isPatrolling
        isPlagued = unit.isPlagued
        isPowered = unit.isPowered
        isRepairing = unit.isRepairing
        isResearching = unit.isResearching
        isSieged = unit.isSieged
        isStartingAttack = unit.isStartingAttack
        isStasised = unit.isStasised
        isStimmed = unit.isStimmed
        isStuck = unit.isStuck
        isTraining = unit.isTraining
        isUnderAttack = unit.isUnderAttack
        isUnderDarkSwarm = unit.isUnderDarkSwarm
        isUnderDisruptionWeb = unit.isUnderDisruptionWeb
        isUnderStorm = unit.isUnderStorm
        isUpgrading = unit.isUpgrading
        isVisible = unit.isVisible
    }
}