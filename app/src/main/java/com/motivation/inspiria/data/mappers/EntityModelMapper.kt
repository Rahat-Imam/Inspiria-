package com.motivation.inspiria.data.mappers

interface EntityModelMapper<Entity,Model> {
    fun entityToModelMapper(entity: Entity): Model
    fun modelToEntityMapper(model: Model): Entity
}