package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.reporting.Reporter

/**
 * Makes the assertion that [AssertionPlant.subject] is (equals) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [AssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Any> Assert<T>.toBe(expected: T)
    = addAssertion(_toBe(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not (does not equal) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [AssertionPlant.subject].
 * Currently the following is possible: `assert(1).notToBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Any> Assert<T>.notToBe(expected: T)
    = addAssertion(_notToBe(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [AssertionPlant.subject].
 * Currently the following is possible: `assert(1).isSame(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Any> Assert<T>.isSame(expected: T)
    = addAssertion(_isSame(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [AssertionPlant.subject].
 * Currently the following is possible: `assert(1).isNotSame(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Any> Assert<T>.isNotSame(expected: T)
    = addAssertion(_isNotSame(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is `null`.
 *
 * @param onlyNullAllowed Has to be `null`.
 *
 * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Any?> AssertionPlantNullable<T>.toBe(@Suppress("UNUSED_PARAMETER") onlyNullAllowed: Nothing?) {
    addAssertion(_isNull(this))
}

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(3).and { isEven(); isGreaterThan(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return This plant to support a fluent API.
 */
infix fun <T : Any> AssertionPlant<T>.and(assertionCreator: Assert<T>.() -> Unit)
    = addAssertionsCreatedBy(assertionCreator)
