/**
 *
 * $Id$
 */
package net.opengis.wfs20.validation;

import net.opengis.wfs20.FeaturesLockedType;
import net.opengis.wfs20.FeaturesNotLockedType;

/**
 * A sample validator interface for {@link net.opengis.wfs20.LockFeatureResponseType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface LockFeatureResponseTypeValidator {
  boolean validate();

  boolean validateFeaturesLocked(FeaturesLockedType value);
  boolean validateFeaturesNotLocked(FeaturesNotLockedType value);
  boolean validateLockId(String value);
}